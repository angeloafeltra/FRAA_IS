package presentazione;

import bean.ImpiegatoBean;
import business.FacadeClassBusiness;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Classe per generare un frame dove è possibile inserire le proprie credenziali ed eseguire un
 * login.
 */
public class LoginView implements LoginInterface {
  // Componenti della view
  private final JFrame framePannello;
  private final JTextField codiceFiscale;
  private final JPasswordField password;
  private final JButton accedi;
  private final JLabel errore;
  private final FacadeClassBusiness business;
  private ControlPanelInterface pannelloDiControllo;
  private ImageIcon immagine;

  /** Costruttore vuoto. */
  public LoginView() {
    framePannello = new JFrame();
    codiceFiscale = new JTextField(16);
    password = new JPasswordField(32);
    accedi = new JButton("Accedi");
    errore = new JLabel();
    immagine = new ImageIcon(getClass().getResource("/image/LogoNoBG.png"));
    business = new FacadeClassBusiness();
    pannelloDiControllo = new ControlPanelView();
  }

  /** Metodo che genera il frame che permette all'impiegato di loggarsi. */
  @Override
  public void showLoginView() {
    // Settaggi frame
    framePannello.setTitle("Pannello di controllo");
    framePannello.setLocationRelativeTo(null);
    framePannello.setSize(400, 400);
    framePannello.setResizable(false);
    framePannello.setLocationRelativeTo(null);
    framePannello.getContentPane().setBackground(Color.white);
    framePannello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon infermiera = new ImageIcon(getClass().getResource("/image/frameIcon.png"));
    framePannello.setIconImage(infermiera.getImage());

    // Settaggi componenti frame
    errore.setForeground(Color.red); // Setto il colore del testo a rosso
    errore.setHorizontalAlignment(JLabel.CENTER); // Centro il testo nella JLabel

    accedi.addActionListener(
        l -> { // Action Listener sul bottone di connessione
          // Verifico le credenziali dell'impiegato
          ImpiegatoBean impiegato =
              business.autenticazione(codiceFiscale.getText(), password.getText());
          if (impiegato != null) {
            business.eliminaPrenotazioniScadute();
            pannelloDiControllo = new ControlPanelView();
            pannelloDiControllo.showControlPanel(impiegato);
            codiceFiscale.setText("");
            password.setText("");
            errore.setText("");
            framePannello.dispose();
          } else { // Creo la prossima view e la rendo visibile
            errore.setText("Credenziali errate");
          } // Messaggio d'errore nel caso in cui le credenziali del
          // impiegato sono sbagliate
        });

    JCheckBox select = new JCheckBox("Mostra password");
    select.setBackground(Color.white); // setto il background della checkbox a bianco
    // Listener sulla checkbox
    select.addItemListener(
        e -> {
          if (!(e.getStateChange()
              == ItemEvent.SELECTED)) { // Se la checkbox non e attiva nascondo la password
            password.setEchoChar('•');
          } else {
            password.setEchoChar((char) 0); // Se la checkbox e attiva mostro la password
          }
        });

    password.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
              accedi.doClick();
            }
          }
        });

    // Scalo le dimensioni dell'immagine medqueue
    Image image = immagine.getImage();
    Image newimg =
        image.getScaledInstance(250, 180, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    immagine = new ImageIcon(newimg);

    JPanel pannelloaccesso = new JPanel();
    pannelloaccesso.setLayout(new GridLayout(7, 1));
    pannelloaccesso.setBackground(Color.white);
    pannelloaccesso.setBorder(BorderFactory.createTitledBorder("Accesso"));
    JLabel insCf = new JLabel("Inserisci il codice fiscale: ");
    pannelloaccesso.add(insCf);
    pannelloaccesso.add(codiceFiscale);
    JLabel insPass = new JLabel("Inserisci la password: ");
    pannelloaccesso.add(insPass);
    pannelloaccesso.add(password);
    pannelloaccesso.add(select);
    pannelloaccesso.add(errore);
    pannelloaccesso.add(accedi);

    framePannello.add(pannelloaccesso, BorderLayout.CENTER);
    JLabel contenitoreImmagine = new JLabel(immagine, JLabel.CENTER);
    framePannello.add(contenitoreImmagine, BorderLayout.NORTH);
    framePannello.setVisible(true);
  }
}
