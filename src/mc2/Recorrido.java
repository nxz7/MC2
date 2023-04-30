package mc2;

/**
 *
 * @author natalia
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.Queue;
import java.util.Stack;


public class Recorrido {

    private gui GG;
    HashMap<Integer, Integer> connectionCache = new HashMap<>();
    private HashMap<Integer, Integer> linea = new HashMap<>();
    HashMap<Integer, HashSet<Integer>> vert;
    private Queue<Integer> queue;
    private Stack<Integer> stack;
    private HashMap<Integer, Integer> fin;
    private HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> pasa;
    private HashMap<Integer, Integer> color;
    private HashMap<Integer, Integer> res;
    private HashSet<Integer> _colors2;
    private ArrayList<Integer> conn;
    private ArrayList<Integer> bconn;
    private ArrayList<Integer> vertice;
    private Color[] vertColors;
    int _seleccionV = -1;
    int _Tam = 20;
    int id = 0;
    int tiempo = 0;
    Integer maxColors = 0;
    int inicio;
    int pFinal;

    public Recorrido(gui GG) {
        this.GG = GG;
        this.vert = new HashMap<>();
        this.queue = new LinkedList<>();
        this.stack = new Stack<>();
        this.vertice = new ArrayList<>();
        this._colors2 = new HashSet<>();
        this.pasa = new HashMap<>();
        this.set = new HashMap<>();
        this.pasa = new HashMap<>();
        this.color = new HashMap<>();
        this.res = new HashMap<>();
        this.vertColors = new Color[]{Color.blue, Color.red, Color.yellow, Color.green, Color.magenta, Color.orange};
    }

    public HashSet<Integer> getEvertice(int inicio2) {
        vert = gui.getVert();
        return vert.get(inicio2);
    }


    void dfs(int inicio2) {
        vert = GG.getVert();
        fin = new HashMap<>();
        pasa = new HashMap<>();

        Iterator<Integer> conjuntoVertices = vert.keySet().iterator();
        while (conjuntoVertices.hasNext()) {
            int punto = conjuntoVertices.next();
            pasa.put(punto, -1);
            fin.put(punto, 0);
        }

        bconn = new ArrayList<>();
        int elemento;
        pasa.put(inicio2, 0); 
        stack.push(inicio2);
        while (!stack.isEmpty()) {
            elemento = stack.peek();

            if (!bconn.contains(elemento)) {
                bconn.add(elemento);
            }
            HashSet<Integer> iList = getEvertice(elemento);
            Iterator<Integer> l = iList.iterator();
            while (l.hasNext()) {
                int n = l.next();
                if (pasa.get(n) == -1) {

                    stack.push(n);
                    pasa.put(n, elemento);
                    fin.put(n, fin.get(elemento) + 1);
                    break;
                }
                if (l.hasNext() == false) {
                    int backEdge = stack.pop();

                }
            }
        }

    }

    void bfs(int inicio2) {
        vert = GG.getVert();
        fin = new HashMap<>();
        pasa = new HashMap<>();
        Iterator<Integer> conjuntoVertices = vert.keySet().iterator();
        while (conjuntoVertices.hasNext()) {
            int punto = conjuntoVertices.next();
            pasa.put(punto, -1);
            fin.put(punto, 0);
        }

        conn = new ArrayList<Integer>();
        int i, elemento;
        pasa.put(inicio2, 0);
        queue.add(inicio2);
        while (!queue.isEmpty()) {
            elemento = queue.remove();

            i = elemento; 
            conn.add(elemento);
            HashSet<Integer> iList = getEvertice(i);
            Iterator<Integer> l = iList.iterator();
            while (l.hasNext()) {
                int n = l.next();
                if (pasa.get(n) == -1) {
                    queue.add(n);
                    pasa.put(n, i);
                    fin.put(n, fin.get(i) + 1);
                }
            }
        }

    }


    boolean verticesUnidos() {
        dfs(inicio);
        Iterator<Integer> conjuntoVertices = vert.keySet().iterator();
        while (conjuntoVertices.hasNext()) {
            int key = conjuntoVertices.next();
            if (pasa.get(key) == -1) {
                return false;
            }
        }
        return true;
    }




    public int fin(int v) {
        return fin.get(v);
    }

    public void masCorto(int v, int e) {
        if (e == v) {
            
            return;
        }
        for (int i = e; i >= 0; i = pasa.get(i)) {
            if (i == v) {
                break;
            }
            if (pasa.get(i) != -1) {
                set.put(pasa.get(i), i);
            }
        }
      
        linea.clear();
        for (int i : set.keySet()) {
            linea.put(i, set.get(i));
        }
        GG.grafo();
    }





    public Queue getQueue() {
        return this.queue;
    }

    public Stack getStack() {
        return this.stack;
    }

    public HashMap getLinea() {
        return this.linea;
    }

    public HashMap fin() {
        return this.fin;
    }

    public HashMap getSet() {
        return this.set;
    }

    public HashMap getPasa() {
        return this.pasa;
    }

    public HashMap getColor() {
        return this.color;
    }

    public HashMap getRes() {
        return this.res;
    }

    public HashSet getColors2() {
        return this._colors2;
    }

    public ArrayList getConn() {
        return this.conn;
    }

    public ArrayList getBConn() {
        return this.bconn;
    }

    public ArrayList getVertice() {
        return this.vertice;
    }

    public Color[] getVertColors() {
        return this.vertColors;
    }

}

