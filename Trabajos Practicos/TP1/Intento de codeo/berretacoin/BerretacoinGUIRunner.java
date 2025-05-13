package berretacoin;
public class BerretacoinGUIRunner {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new BerretacoinGUI();
        });
    }
}
