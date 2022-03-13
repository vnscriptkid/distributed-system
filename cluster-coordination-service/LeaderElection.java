import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LeaderElection implements Watcher {
    public static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    public static final int SESSION_TIMEOUT = 3000;
    public static final String ELECTION_NAMESPACE = "/election";
    private ZooKeeper zooKeeper;
    private String currentZnodeName;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        LeaderElection leaderElection = new LeaderElection();

        leaderElection.connectToZookeeper();
        leaderElection.volunteerForLeadership();
        leaderElection.reelectLeader();

        // prevent main thread to exit
        leaderElection.run();
        leaderElection.close();
        System.out.println("Disconnected from zookeeper");
    }

    public void volunteerForLeadership() throws InterruptedException, KeeperException {
        String zNodePrefix = ELECTION_NAMESPACE + "/c_";
        String zNodeFullPath = zooKeeper.create(zNodePrefix, new byte[] {},
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("znode name: " + zNodeFullPath);
        this.currentZnodeName = zNodeFullPath.replace(ELECTION_NAMESPACE + "/", "");
    }

    public void reelectLeader() throws InterruptedException, KeeperException {
        Stat predecessorStat = null;
        String predecessorZnodeName = "";

        while (predecessorStat == null) {
            List<String> children = zooKeeper.getChildren(ELECTION_NAMESPACE, false);

            Collections.sort(children);
            String smallestChild = children.get(0);
            if (smallestChild.equals(currentZnodeName)) {
                System.out.println("I am the leader");
                return;
            } else {
                System.out.println("I am NOT the leader");
                int predecessorIdx = Collections.binarySearch(children, currentZnodeName) - 1;
                predecessorZnodeName = children.get(predecessorIdx);

                // Potentially race condition here (predecessor gets deleted before executing line below)
                predecessorStat = zooKeeper.exists(ELECTION_NAMESPACE + "/" + predecessorZnodeName, this);
            }
        }

        System.out.println("Watching node: " + predecessorZnodeName);
        System.out.println();
    }

    public void connectToZookeeper( ) throws IOException {
        this.zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, this);
    }

    public void run() throws InterruptedException {
        synchronized (zooKeeper) {
            zooKeeper.wait();
        }
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // this will run in EventThread
        switch (watchedEvent.getType()) {
            case None:
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Successfully connected to zookeeper");
                } else {
                    synchronized (zooKeeper) {
                        // disconnected to zookeeper, wake up main the thread (that is in wait state)
                        System.out.println("Can not connect to zookeeper");
                        zooKeeper.notifyAll();
                    }
                }
            case NodeDeleted:
                try {
                    reelectLeader();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
        }
    }
}
