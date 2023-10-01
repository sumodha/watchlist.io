
// IMPORTS 
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUI {

	public static void main(String[] args) throws IOException {

		initialize();
		welcome();

	}

	private static JPanel welcomePanel;
	public static JPanel watchlistsPanel;
	public static JPanel namePanel;
	private static JButton addButton;
	private static JLabel name;
	private static JPanel addPanel;
	public static ArrayList<Watchlist> watchlistsArray;
	public static JFrame frame;
	public static File f;

	public static void initialize() {

		watchlistsArray = new ArrayList<Watchlist>();

		// FRAME
		frame = new JFrame(); // creating a new frame
		frame.setTitle("watchlist.io"); // with the title "watchlist.io"
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // places panels in one, single column
		frame.setMinimumSize(new Dimension(850, 650));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		f = new File("watchlist.io.txt");
		// add a window listener to detect when the user closes the frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// catching io exception
				try {
					save(f, watchlistsArray); // saving info from current run
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// exiting the application
				System.exit(0);
			}
		});

	}

	public static void welcome() {

		// WELCOME PANEL
		welcomePanel = new JPanel();
		welcomePanel.setLayout(new GridBagLayout());

		// name
		JLabel name = new JLabel("watchlist.io");
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		name.setFont(new Font("Monospaced", Font.BOLD, 100));
		name.setBorder(new EmptyBorder(10, 10, 10, 10));

		// start
		JButton start = new JButton("start");
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		start.setFont(new Font("Monospaced", Font.PLAIN, 30));
		start.setBorder(new EmptyBorder(10, 10, 10, 10));
		start.addActionListener(e -> {
			// catching io exception
			try {
				initializeWatchlist();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		// panel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(name);
		panel.add(start);

		// adding panel to welcome panel
		welcomePanel.add(panel);

		// FRAME
		// adding welcome panel to frame
		frame.getContentPane().add(welcomePanel);
		frame.setVisible(true);
	}

	public static void initializeWatchlist() throws IOException {

		// removing welcome panel from frame
		frame.remove(welcomePanel);

		// NAME
		name = new JLabel("watchlist.io / watchlists");
		name.setFont(new Font("Monospaced", Font.ITALIC, 20));

		// NAME PANEL
		namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		namePanel.setBorder(new EmptyBorder(20, 15, 20, 20));
		namePanel.setPreferredSize(new Dimension(700, 75));

		// adding name to name panel
		namePanel.add(name);

		// ADD BUTTON
		addButton = new JButton(new ImageIcon("add.png"));
		addButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
		addButton.setBorder(new EmptyBorder(10, 10, 10, 10));
		addButton.addActionListener(e -> addWatchlist());

		// ADD PANEL
		addPanel = new JPanel(new BorderLayout());
		addPanel.setBorder(
				new CompoundBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.BLACK, 2)),
						new EmptyBorder(10, 10, 10, 10)));
		addPanel.setPreferredSize(new Dimension(250, 250));

		// adding add button to add panel
		addPanel.add(addButton, BorderLayout.CENTER);

		// WATCHLISTS PANEL
		watchlistsPanel = new JPanel();
		watchlistsPanel.setPreferredSize(new Dimension(500, 500));
		watchlistsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		watchlistsPanel.setLayout(new GridLayout(0, 2));

		// adding add panel to watch lists panel
		watchlistsPanel.add(addPanel);

		// UPDATING INFO FROM PREVIOUS RUN
		Scanner scan = new Scanner(f);

		while (scan.hasNextLine()) {
			String watchlistTitle = scan.nextLine();
			Watchlist w = new Watchlist(watchlistTitle);

			int showCount = Integer.parseInt(scan.nextLine());

			for (int i = 0; i < showCount; i++) {
				String title = scan.nextLine();
				String status = scan.nextLine();
				String genre = scan.nextLine();
				String tags = scan.nextLine();
				String rating = scan.nextLine();
				new Show(new String[] { title, status, genre, tags, rating }, w);
			}

		}

		scan.close();

		toWatchlists();

	}

	public static void toWatchlists() {

		// ADDING NAME PANEL & WATCHLISTS PANEL TO FRAME
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(namePanel, BorderLayout.NORTH);
		frame.getContentPane().add(watchlistsPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();

	}

	public static void addWatchlist() {

		JTextField title = new JTextField();

		Object[] message = { "title: ", title,

		};

		int option = JOptionPane.showConfirmDialog(null, message, "add watchlist", JOptionPane.OK_CANCEL_OPTION);

		if (JOptionPane.OK_OPTION == option) {
			new Watchlist(title.getText());
		}

	}

	public String stars(int n) {
		
		if (n > 10) {
			n = 10;
		}
		if (n < 0 ) {
			n = 0;
		}
		String stars = "";

		for (int i = 0; i < n; i++) {
			stars += "★";
		}

		for (int i = 0; i < 10 - n; i++) {
			stars += "☆";
		}

		return stars;
	}

	public static void save(File f, ArrayList<Watchlist> wl) throws IOException {

		// deleting original file, creating a new file, and traversing the array list
		// onto it
		f.delete();
		f.createNewFile();
		FileWriter writer = new FileWriter(f);
		for (Watchlist w : wl) {
			writer.write(w.getTitle() + "\n" + w.shows.size() + "\n");
			for (Show s : w.shows) {
				for (int i = 0; i < 5; i++) {
					writer.write(s.getShow()[i] + "\n");
				}
			}
		}
		writer.close();

	}

}
