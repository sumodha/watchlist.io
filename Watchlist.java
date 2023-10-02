
// IMPORTS
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Watchlist extends GUI {

	// IN WATCHLISTS PANEL
	private JButton watchlistButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel watchlist;

	// IN WATCHLIST PANEL
	public ArrayList<Show> shows;

	private String title;
	private JLabel titleLabel;
	private JLabel sortBy;
	private JComboBox<String> sortOptions;
	private JPanel sortPanel;
	private JButton addButton;
	private JPanel addPanel;
	private JButton back;
	public JPanel watchlistPanel;
	private JScrollPane scrollPane;

	public Watchlist(String t) {
		// WATCHLIST BUTTON
		watchlistButton = new JButton(t);
		watchlistButton.addActionListener(e -> watchlist());
		watchlistButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
		watchlistButton.setHorizontalAlignment(JButton.CENTER);
		watchlistButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		// UPDATE BUTTON
		updateButton = new JButton(new ImageIcon("update.png"));
		updateButton.addActionListener(e -> update());
		updateButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		updateButton.setFont(new Font("Monospaced", Font.PLAIN, 10));
		updateButton.setHorizontalAlignment(JButton.RIGHT);

		// DELETE BUTTON
		deleteButton = new JButton(new ImageIcon("delete.png"));
		deleteButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		deleteButton.addActionListener(e -> delete());
		deleteButton.setFont(new Font("Monospaced", Font.PLAIN, 10));
		deleteButton.setHorizontalAlignment(JButton.RIGHT);

		// WATCHLIST
		watchlist = new JPanel();
		watchlist.setLayout(new BorderLayout());
		watchlist.setBorder(
				new CompoundBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.BLACK, 2)),
						new EmptyBorder(10, 10, 10, 10)));

		watchlist.add(watchlistButton, BorderLayout.CENTER);

		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttons.add(updateButton);
		buttons.add(deleteButton);
		watchlist.add(buttons, BorderLayout.SOUTH);

		watchlistsPanel.add(watchlist); // adding this instance of watch list panel to watch lists panel

		frame.add(watchlistsPanel); // adding updated watch lists panel to frame

		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();

		// SHOWS
		shows = new ArrayList<Show>();

		// TITLE
		title = t;

		// TITLE LABEL
		titleLabel = new JLabel("watchlist.io / " + t + "                         ");
		titleLabel.setFont(new Font("Monospaced", Font.ITALIC, 20));
		titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		// SORT BY
		sortBy = new JLabel(new ImageIcon("sort.png"));
		sortBy.setFont(new Font("Monospaced", Font.PLAIN, 20));
		sortBy.setAlignmentX(Component.RIGHT_ALIGNMENT);

		// SORT OPTIONS
		sortOptions = new JComboBox<String>(new String[] { "title (A-Z)", "title (Z-A)", "status (A-Z)", "status (Z-A)",
				"genre (A-Z)", "genre (Z-A)", "tags (A-Z)", "tags (Z-A)", "rating (0-10)", "rating (10-0)" });
		sortOptions.setAlignmentX(Component.RIGHT_ALIGNMENT);
		sortOptions.setBorder(new EmptyBorder(3, 0, 0, 0));
		sortOptions.addActionListener(e -> sorter());

		// SORT PANEL
		sortPanel = new JPanel();
		sortPanel.setLayout(new GridBagLayout());
		sortPanel.setBorder(new EmptyBorder(27, 20, 20, 20));
		sortPanel.setPreferredSize(new Dimension(700, 75));

		// adding components to sort panel
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 1.0;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		sortPanel.add(titleLabel, c);

		c.weighty = 0.0;
		c.weightx = 0.0;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		sortPanel.add(sortBy, c);

		c.weighty = 0.0;
		c.weightx = 0.0;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 2;
		sortPanel.add(sortOptions, c);

		// ADD BUTTON
		addButton = new JButton(new ImageIcon("add.png"));
		addButton.addActionListener(e -> add());
		addButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
		addButton.setBorder(new EmptyBorder(10, 10, 10, 10));

		// ADD PANEL
		addPanel = new JPanel(new BorderLayout());
		addPanel.setBorder(
				new CompoundBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.BLACK, 2)),
						new EmptyBorder(10, 10, 10, 10)));
		addPanel.add(addButton, BorderLayout.CENTER);

		addPanel.setMinimumSize(new Dimension(300, 300));

		// BACK
		back = new JButton(new ImageIcon("back.png"));
		back.addActionListener(e -> backToWatchlists());
		back.setFont(new Font("Monospaced", Font.PLAIN, 15));
		back.setBorder(new EmptyBorder(10, 10, 10, 10));

		// WATCHLIST PANEL
		watchlistPanel = new JPanel();
		watchlistPanel.setLayout(new GridLayout(0, 2));
		watchlistPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		watchlistPanel.add(addPanel);

		// SCROLL PANE
		// Creating a JScrollPane
		scrollPane = new JScrollPane();
		// Setting the watch list panel as its view
		scrollPane.setViewportView(watchlistPanel);
		// Setting the vertical and horizontal scroll bar policy to ALWAYS
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		// Setting the scroll speed to be faster
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.getVerticalScrollBar().setBlockIncrement(200);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
		scrollPane.getHorizontalScrollBar().setBlockIncrement(200);

		watchlistsArray.add(this); // adding this instance of watch list to watch lists array
	}

	// GET METHODS
	public String getTitle() {
		return title;
	}

	public JPanel getWatchlist() {
		return watchlist;
	}

	public void watchlist() {
		// REMOVING WATCHLISTS PANEL
		frame.remove(namePanel);
		frame.remove(watchlistsPanel);

		// ADDING THIS INSTANCE OF WATCH LIST PANEL
		frame.getContentPane().add(sortPanel, BorderLayout.NORTH);
		// frame.getContentPane().add(watchlistPanel, BorderLayout.CENTER);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(back, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();

	}

	public void update() {
		JTextField titleField = new JTextField();
		Object[] message = { "title: ", titleField, };

		int option = JOptionPane.showConfirmDialog(null, message, "update watchlist name",
				JOptionPane.OK_CANCEL_OPTION);

		if (JOptionPane.OK_OPTION == option) {
			// UPDATING TITLE, TITLE LABEL & WATCHLIST BUTTON TO NEW WATCH LIST NAME
			title = titleField.getText();
			titleLabel.setText("watchlist.io / " + title + "     ");
			watchlistButton.setText(title);
		}
	}

	public void delete() {
		int input = JOptionPane.showConfirmDialog(null, "are you sure you want to delete " + title + "?", "∠(´•０•`)〴",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (input == JOptionPane.YES_OPTION) {
			// REMOVING THIS INSTANCE OF WATCHLISTS FROM WATCH LISTS PANEL & WATCHLISTS
			// ARRAY
			watchlistsPanel.remove(watchlist);
			watchlistsArray.remove(this);
			watchlistsPanel.revalidate();
			watchlistsPanel.repaint();
		}

	}

	public void backToWatchlists() {

		// frame.remove(watchlistPanel);
		frame.remove(scrollPane);
		frame.remove(sortPanel);
		frame.remove(back);

		toWatchlists(); // calling to watch lists (a method in GUI)

	}

	public void add() {

		JTextField title = new JTextField();
		JComboBox<String> status = new JComboBox<String>(
				new String[] { "recommended", "planning to watch", "watching", "completed" });
		JTextField genre = new JTextField();
		JTextField tags = new JTextField();
		JTextField rating = new JTextField();

		Object[] message = { "title: ", title, "status: ", status, "genre: ", genre, "tags: ", tags, "rating:",
				rating };

		int option = JOptionPane.showConfirmDialog(null, message, "add show", JOptionPane.OK_CANCEL_OPTION);

		String[] showInfo = new String[5];
		if (JOptionPane.OK_OPTION == option) {
			// CREATING A NEW SHOW OBJECT BASED ON USER INPUT
			showInfo[0] = title.getText();
			showInfo[1] = (String) status.getSelectedItem();
			showInfo[2] = genre.getText();
			showInfo[3] = tags.getText();
			showInfo[4] = rating.getText();
			new Show(showInfo, this);
		}

	}

	public void sorter() {
		deletePanels();
		String sortChoice = (String) sortOptions.getSelectedItem(); // sort choice is based on what the user clicks in
																	// the sort options combo box

		switch (sortChoice) {

		case "title (A-Z)":
			sort(shows, 0, true);
			break;

		case "title (Z-A)":
			sort(shows, 0, false);
			break;

		case "status (A-Z)":
			sort(shows, 1, true);
			break;

		case "status (Z-A)":
			sort(shows, 1, false);
			break;

		case "genre (A-Z)":
			sort(shows, 2, true);
			break;

		case "genre (Z-A)":
			sort(shows, 2, false);
			break;

		case "tags (A-Z)":
			sort(shows, 3, true);
			break;

		case "tags (Z-A)":
			sort(shows, 3, false);
			break;

		case "rating (0-10)":
			ratingSort(shows, true);
			break;

		case "rating (10-0)":
			ratingSort(shows, false);
			break;

		}

		addPanels();

	}

	public void sort(ArrayList<Show> wl, int ind, boolean ascend) {
		String val;
		int pos;

		for (int i = 0; i < wl.size(); i++) {
			val = wl.get(i).getShow()[ind];
			pos = i;
			for (int j = i + 1; j < wl.size(); j++) {
				if (ascend ? wl.get(j).getShow()[ind].compareTo(val) < 0 // ascending order vs descending order
						: wl.get(j).getShow()[ind].compareTo(val) > 0) {
					val = wl.get(j).getShow()[ind];
					pos = j;
				}
			}
			Show temp = wl.get(i);
			wl.set(i, wl.get(pos));
			wl.set(pos, temp);
		}

	}

	public void ratingSort(ArrayList<Show> wl, boolean ascend) {
		int val;
		int pos;

		for (int i = 0; i < wl.size(); i++) {
			val = Integer.parseInt(wl.get(i).getShow()[4]);
			pos = i;
			for (int j = i + 1; j < wl.size(); j++) {
				if (ascend ? Integer.parseInt(wl.get(j).getShow()[4]) < val // ascending order vs descending order
						: Integer.parseInt(wl.get(j).getShow()[4]) > val) {
					val = Integer.parseInt(wl.get(i).getShow()[4]);
					pos = j;
				}
			}
			Show temp = wl.get(i);
			wl.set(i, wl.get(pos));
			wl.set(pos, temp);
		}

	}

	public void deletePanels() {

		for (int i = 0; i < shows.size(); i++) {
			// DELETES ALL SHOW PANELS FROM WATCHLIST PANEL
			watchlistPanel.remove(shows.get(i).getPanel());
			watchlistPanel.revalidate();
			watchlistPanel.repaint();
		}
	}

	public void addPanels() {

		for (int i = 0; i < shows.size(); i++) {
			// ADDS ALL SHOW PANELS IN SHOW ARRAY LIST TO WATCHLIST PANEL
			watchlistPanel.add(shows.get(i).getPanel());
			watchlistPanel.revalidate();
			watchlistPanel.repaint();
		}
	}

}
