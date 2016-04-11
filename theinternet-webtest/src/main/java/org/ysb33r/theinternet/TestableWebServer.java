package org.ysb33r.theinternet;

import org.jruby.mains.JarMain;

/**
 * @author Schalk W. Cronjé
 */
public class TestableWebServer implements Runnable {

    public TestableWebServer() {

    }

    /** Instantiates the server with a specific port number
     *
     * @param portno
     */
    public TestableWebServer(int portno) {
        port = portno;
    }

    /** Get the port number this server has been given to start on.
     *
     * @return Port number
     */
    int getPort() {
        return port;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        JarMain.main("the-internet/server.rb",String.valueOf(port));
    }

    public void start() {
        service = new Thread(this);
        service.start();
    }

    public void stop() throws InterruptedException {
        if(service != null) {
            // Should not really stop a thread, but currently
            // we don't have another way of tell JRuby (via JarMain) to stop
            // what it is doing.
            service.stop();
            service = null;
        }
    }

    /** Entry point if run from command-line. Do not use this if called from code.
     * Use {@link #start} instead.
     * @param args Ignored
     */
    static public void main(String[] args) {
        if(args.length > 0) {
            new TestableWebServer(Integer.parseInt(args[0])).run();
        } else {
            new TestableWebServer().run();
        }
    }

    private Thread service;
    int port = 4567;
}
