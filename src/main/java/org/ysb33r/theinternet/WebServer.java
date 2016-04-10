package org.ysb33r.theinternet;

import org.jruby.embed.ScriptingContainer;
import static org.jruby.embed.PathType.*;
import org.jruby.CompatVersion;

import java.util.ArrayList;
import java.util.List;

public class WebServer {

    public WebServer() throws Exception {
        container = new ScriptingContainer();
//        container.setArgv(args);
//        container.setEnvironment(Map environment)

        // TODO: Do we need to do anything extra for load paths ?
        List<String> loadPaths = new ArrayList();
        loadPaths.add("the-internet");
        container.setLoadPaths(loadPaths);

    }

    int run() {
        try {
            Object outcome = container.runScriptlet(
                    getClass().getResourceAsStream("the-internet/server.rb"),
                    "server.rb"
            );
            return ( outcome instanceof Number ) ? ( (Number) outcome ).intValue() : 0;
        } finally {
            container.terminate();
        }
    }

    private ScriptingContainer container;

    public static void main(String[] args) throws Exception {
        WebServer jm = new WebServer();
        System.exit(jm.run());
    }

}