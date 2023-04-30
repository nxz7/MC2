
package mc2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Queue;

import java.util.Stack;

import javax.swing.SwingUtilities;
//import javax.swing.Timer;


public class gui extends javax.swing.JFrame {

    HashMap<Integer, Integer> connectionCache = new HashMap<>();
    HashMap<Integer, Integer> linea;
    private static HashMap<Integer, HashSet<Integer>> vert = new HashMap();
    Queue<Integer> queue;
    Stack<Integer> stack;
    HashMap<Integer, Point> lugarP = new HashMap();
    HashMap<Integer, Integer> fin;
    Map<Integer, Integer> set;
    HashMap<Integer, Integer> pasa;
    HashMap<Integer, Integer> color;
    HashMap<Integer, Integer> fcolors;
    HashMap<Integer, Integer> res;
    HashSet<Integer> _colors2;
    HashSet<Integer> rPuntos;
    ArrayList<Integer> conn;
    ArrayList<Integer> bconn;
    ArrayList<Integer> vertice;
    Color[] vertColors;
    int _seleccionV = -1;
    int _Tam = 20;
    int id = 0;
    int tiempo = 0;
    Integer maxColors = 0;
    int inicio = -1;
    int pFinal = -1;
    Image bufferImage;
    Graphics2D bufferGraphic;
    
    boolean graficoHecho = false;
    
    double dotOffset = 0.0;
    Recorrido alg;

    public gui() {
        initComponents();
        bufferImage = createImage(pnlGrafo.getWidth() - 2, pnlGrafo.getHeight() - 2);
        bufferGraphic = (Graphics2D) bufferImage.getGraphics();
        this.alg = new Recorrido(this);
        queue = alg.getQueue();
        stack = alg.getStack();
        vertice = alg.getVertice();
        color = alg.getColor();
        _colors2 = alg.getColors2();
        pasa = alg.getPasa();
        res = alg.getRes();
        linea = alg.getLinea();
        set = alg.getSet();
        fin = alg.fin();
        vertColors = new Color[]{Color.blue, Color.red, Color.yellow, Color.green, Color.magenta, Color.orange};
        rPuntos = new HashSet<Integer>();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               
                    System.exit(0);
                
            }
        });
    }

    public static HashMap getVert() {
        return gui.vert;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        pnlGrafo = new javax.swing.JPanel();
        btnReset = new javax.swing.JButton();

        lblInfo = new java.awt.Label();
        lblResult = new javax.swing.JLabel();
 

        jcbAlgo = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();



        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        pnlGrafo.setBackground(new java.awt.Color(153, 50, 204));
        pnlGrafo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlGrafo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlGrafoPresAr(evt);
            }
        });
        pnlGrafo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGrafoInicioFin(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlGrafoClickVertice(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlGrafoArista(evt);
            }
        });
        pnlGrafo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlGraphComponentResized(evt);
            }
        });

        javax.swing.GroupLayout pnlGraphLayout = new javax.swing.GroupLayout(pnlGrafo);
        pnlGrafo.setLayout(pnlGraphLayout);
        pnlGraphLayout.setHorizontalGroup(
                pnlGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlGraphLayout.setVerticalGroup(
                pnlGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 282, Short.MAX_VALUE)
        );

btnReset.setText("Limpiar");
btnReset.setBackground(new Color(137, 207, 240)); 
btnReset.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnResetActionPerformed(evt);
    }
});


     
 

        lblInfo.setText("Vertice inicial: Ninguno - Vertice Final: Ninguno");



       

 jcbAlgo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Corto", "Adicional" }));
jcbAlgo.setBackground(new Color(255, 255, 153)); 
jcbAlgo.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jcbAlgoActionPerformed(evt);
    }
});


btnBuscar.setText("Buscar");
btnBuscar.setBackground(new Color(204, 153, 255)); 
btnBuscar.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnBuscarActionPerformed(evt);
    }
});



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnReset)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                                        .addGap(44, 44, 44)
                                        .addComponent(jcbAlgo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
)
                                .addComponent(pnlGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblResult))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnReset)

                                .addComponent(jcbAlgo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscar))
                        .addContainerGap())
        );

        pack();
    } 
    
//click en el panel del grafo
    private void pnlGrafoClickVertice(java.awt.event.MouseEvent evt) {
        _seleccionV = verticeSeleccionado(evt.getX(), evt.getY());
        if (_seleccionV < 0 && SwingUtilities.isLeftMouseButton(evt)) {
            graficoHecho = true;
            vert.put(id, new HashSet());
            lugarP.put(id++, new Point(evt.getX(), evt.getY()));
        } else if (SwingUtilities.isLeftMouseButton(evt)) {
        } else if (SwingUtilities.isRightMouseButton(evt)) {
            graficoHecho = true;

            inicio = -1;
            pFinal = -1;
            vert.remove(_seleccionV);
            lugarP.remove(_seleccionV);

            for (HashSet<Integer> connections : vert.values()) {
                for (int j = 0; j < connections.size(); j++) {
                    Integer connection = (Integer) connections.toArray()[j];
                    if (connection == _seleccionV) {
                        connections.remove(connection);
                        j--;
                    }
                }
            }
            if (_seleccionV == pFinal) {
                pFinal = -1;
                linea.clear();
            }
            if (_seleccionV == inicio) {
                inicio = -1;
                pFinal = -1;
                linea.clear();
            }
            _seleccionV = -1;
        }
        grafo();
    }

    private void pnlGrafoPresAr(java.awt.event.MouseEvent evt) {
        if (_seleccionV >= 0) {
            if (SwingUtilities.isLeftMouseButton(evt)) {
                Image buff = createImage(pnlGrafo.getWidth() - 1, pnlGrafo.getHeight() - 1);
                Graphics buffG = buff.getGraphics();
                buffG.drawImage(bufferImage, 0, 0, this);
                Point source = lugarP.get(_seleccionV);
                buffG.drawLine(source.x, source.y,
                        evt.getX(), evt.getY());
                pnlGrafo.getGraphics().drawImage(buff, 1, 1, this);
            } else if (SwingUtilities.isMiddleMouseButton(evt)) {
                lugarP.get(_seleccionV).x = evt.getX();
                lugarP.get(_seleccionV).y = evt.getY();
                grafo();
                graficoHecho = true;
            }
        }
    }

    private void pnlGraphComponentResized(java.awt.event.ComponentEvent evt) {
        bufferImage = createImage(pnlGrafo.getWidth() - 2, pnlGrafo.getHeight() - 2);
        bufferGraphic = (Graphics2D) bufferImage.getGraphics();
    }
//se crea una arista >>> grafo
    
    private void pnlGrafoArista(java.awt.event.MouseEvent evt) {
        if (_seleccionV >= 0) {
            int destination = verticeSeleccionado(evt.getX(), evt.getY());
            if (destination >= 0 && destination != _seleccionV) {
                vert.get(_seleccionV).add(destination);
                vert.get(destination).add(_seleccionV);
                _seleccionV = -1;
                graficoHecho = true;
            }
        }
        grafo();
    }



    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        graficoHecho = true;
        borrar();
    }

    
    
    private void pnlGrafoInicioFin(java.awt.event.MouseEvent evt) {
        _seleccionV = verticeSeleccionado(evt.getX(), evt.getY());
        if (evt.getClickCount() == 2) {
            if (inicio == -1 && pFinal == -1
                    || inicio != -1 && pFinal != -1) {
                linea.clear();
                inicio = _seleccionV;
                pFinal = -1;
            } else if (inicio != _seleccionV) {
                linea.clear();
                pFinal = _seleccionV;

                set.clear();
            }
            grafo();
        }
    }

    private void borrar() {
        vert = new HashMap();
        lugarP = new HashMap();
        id = 0;
        vertice = new ArrayList<Integer>();
        alg.getVertice().clear();
        _colors2 = new HashSet<Integer>();
        alg.getColors2().clear();
        linea.clear();
        alg.getLinea().clear();
        res.clear();
        alg.getRes().clear();
        inicio = -1;
        pFinal = -1;
        grafo();
    }



    private void jcbAlgoActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String x = String.valueOf(jcbAlgo.getSelectedItem());
        linea.clear();
        alg.getLinea().clear();

        pasa.clear();
        alg.getPasa().clear();
        set.clear();
        alg.getSet().clear();
        res.clear();
        alg.getRes().clear();
        vertice.clear();
        alg.getVertice().clear();
         if (x == "Adicional") {

            

            alg.dfs(inicio);
            alg.masCorto(inicio, pFinal);
            
            
        } else if (x == "Corto") {

            
            alg.bfs(inicio);
            alg.masCorto(inicio, pFinal);
        
        } 

    }




 

    private String getVerticeInfo(int vertId) {
        if (vertId == -1) {
            return "Ninguno";
        }
        return "" + vertId;
    }

public void grafo() {
    bufferGraphic.setColor(new Color(228, 251, 249)); 
    bufferGraphic.fillRect(0, 0, pnlGrafo.getWidth(), pnlGrafo.getHeight());
    connectionCache.clear();
    
        bufferGraphic.setColor(Color.black);
        bufferGraphic.setStroke(new BasicStroke(2));
        for (int i = 0; i < lugarP.size(); i++) {
            Integer sourceKey = (Integer) vert.keySet().toArray()[i];
            Point thePoint = (Point) lugarP.values().toArray()[i];
            for (Integer destinationKey
                    : (HashSet<Integer>) vert.values().toArray()[i]) {
                if (!(connectionCache.containsKey(sourceKey)
                        && connectionCache.get(sourceKey) == destinationKey
                        || connectionCache.containsKey(destinationKey)
                        && connectionCache.get(destinationKey) == sourceKey)) {
                    Point destinantionPoint = lugarP.get(destinationKey);
                    bufferGraphic.drawLine(thePoint.x, thePoint.y,
                            destinantionPoint.x, destinantionPoint.y);
                    connectionCache.put(sourceKey, destinationKey);
                }
            }
        }

        // LINEA - arista
        bufferGraphic.setColor(new Color(95, 2, 112 ));
        bufferGraphic.setStroke(new BasicStroke(7));
        if (!btnReset.isSelected()) {
            for (int sourceKey : linea.keySet()) {
                int destKey = linea.get(sourceKey);
                Point sourcePoint = (Point) lugarP.get(sourceKey);
                Point destPoint = (Point) lugarP.get(destKey);
                aristaRes(bufferGraphic, sourcePoint, destPoint, dotOffset);
            }
        }

        // Vertices 
        for (int i = 0; i < lugarP.size(); i++) {
            Point thePoint = (Point) lugarP.values().toArray()[i];

            if (lugarP.keySet().toArray()[i].equals((Integer) inicio)) {
                bufferGraphic.setColor(Color.green);
            } else if (lugarP.keySet().toArray()[i].equals((Integer) pFinal)) {
                bufferGraphic.setColor(Color.blue);
            } else if (lugarP.keySet().toArray()[i].equals((Integer) _seleccionV)) {
                bufferGraphic.setColor(Color.orange);
            } else {
                bufferGraphic.setColor(Color.red);
            }
            if (res.size() > 0) {
                Integer k = (Integer) vert.keySet().toArray()[i];
                if (res.get(k) == 0) {
                    bufferGraphic.setColor(vertColors[0]);
                } else if (res.get(k) == 1) {
                    bufferGraphic.setColor(vertColors[1]);
                } else if (res.get(k) == 2) {
                    bufferGraphic.setColor(vertColors[2]);
                } else if (res.get(k) == 3) {
                    bufferGraphic.setColor(vertColors[3]);
                } else if (res.get(k) == 4) {
                    bufferGraphic.setColor(vertColors[4]);
                } else if (res.get(k) == 5) {
                    bufferGraphic.setColor(vertColors[5]);
                }
            }

            if (vertice.contains(lugarP.keySet().toArray()[i])) {
                bufferGraphic.setColor(Color.gray);
            }

            bufferGraphic.fillOval(thePoint.x - _Tam/ 2,
                    thePoint.y - _Tam / 2, _Tam, _Tam);
        }

        // Vertices
        bufferGraphic.setColor(Color.green);
        for (int i = 0; i < lugarP.size(); i++) {
            Point thePoint = (Point) lugarP.values().toArray()[i];
            bufferGraphic.drawString("V " + lugarP.keySet().toArray()[i],
                    thePoint.x + _Tam + 4, thePoint.y + _Tam + 4);
        }
        pnlGrafo.getGraphics().drawImage(bufferImage, 1, 1, this);
        lblInfo.setText("V. inicial: " + getVerticeInfo(inicio)
                + " - V. Final: " + getVerticeInfo(pFinal));
    }

private void aristaRes(Graphics2D g, Point p1, Point p2, double offset) {
    long total = (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    total = (long) Math.sqrt(total);
    int x1 = p1.x;
    int y1 = p1.y;
    int x2 = p2.x;
    int y2 = p2.y;
    g.drawLine(x1, y1, x2, y2);
}


    private int verticeSeleccionado(int x, int y) {
        for (int i = 0; i < lugarP.size(); i++) {
            Point thePoint = (Point) lugarP.values().toArray()[i];
            int deltaX = x - (thePoint.x - _Tam / 2);
            int deltaY = y - (thePoint.y - _Tam / 2);
            if (Math.sqrt(deltaX * deltaX
                    + deltaY * deltaY) <= _Tam + 6) {
                return (int) lugarP.keySet().toArray()[i];
            }
        }
        return -1;
    }





    @Override
    public void paint(Graphics g) {
        super.paint(g);
        grafo();
    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui theGraph = new gui();
                theGraph.setLocationRelativeTo(null);
                theGraph.show();
            }
        });
    }

  

    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnBuscar;


    private javax.swing.JComboBox<String> jcbAlgo;
    private java.awt.Label lblInfo;
    private javax.swing.JLabel lblResult;


    private javax.swing.JPanel pnlGrafo;
 
  }  

