package com.sreekanth.compilerdesign.visualization;

import com.sreekanth.compilerdesign.models.Automata;
import com.sreekanth.compilerdesign.models.Transition;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;

public class AutomataVisualization {

    public static void displayAutomata(Automata automata) {

        Graph graph = new MultiGraph("Automata");
        graph.setAttribute("ui.stylesheet", "url('graph.css')");

        for(Integer state: automata.getStates()) {
            Node n = graph.addNode(state.toString());
            n.setAttribute("ui.label", state);
        }

        for(Transition t: automata.getTransitions()) {

            String sourceNode = t.getFromState().toString();
            String targetNode = t.getToState().toString();
            Edge e = graph.addEdge(sourceNode+targetNode, sourceNode, targetNode, true);

            e.setAttribute("ui.label", t.getAction());
        }

        Viewer viewer = graph.display();
    }
}
