
/* CITING ICONS
 * UPDATE ICON from Kiranshastry
 * DELETE ICON from Tanah Basah
 * ADD ICON from Pixel Perfect
 * BACK ICON from Free pik
 * SORT ICON from Tempo_doloe
*/

// IMPORTS
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Show extends GUI {
	private String[] show;
	private JLabel titleLabel;
	private JLabel statusLabel;
	private JLabel genreLabel;
	private JLabel tagsLabel;
	private JLabel ratingLabel;
	private JButton updateButton;
	private JButton deleteButton;
	public JPanel showPanel;

	public Show(String[] s, Watchlist w) {
		// SHOW
		show = s;
		// show[0] = title 
		// show[1] = status 
		// show[2] = genre 
		// show[3] = tags 
		// show[4] = rating

		// TITLE LABEL
		titleLabel = new JLabel(show[0]);
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
		titleLabel.setHorizontalAlignment(JLabel.LEFT);

		// STATUS LABEL
		statusLabel = new JLabel(show[1]);
		statusLabel.setFont(new Font("Monospaced", Font.ITALIC, 20));
		statusLabel.setHorizontalAlignment(JLabel.LEFT);

		// GENRE LABEL
		genreLabel = new JLabel(show[2]);
		genreLabel.setFont(new Font("Monospaced", Font.PLAIN, 10));
		genreLabel.setHorizontalAlignment(JLabel.LEFT);

		// TAGS LABEL
		tagsLabel = new JLabel(show[3]);
		tagsLabel.setFont(new Font("Monospaced", Font.PLAIN, 10));
		tagsLabel.setHorizontalAlignment(JLabel.LEFT);

		// RATING LABEL
		if (show[4].isEmpty() || Integer.parseInt(show[4]) == 0) {
			show[4] = "0";
			ratingLabel = new JLabel(""); // stars(0)

		} else
			ratingLabel = new JLabel(stars(Integer.parseInt(show[4])));

		ratingLabel.setFont(new Font("Monospaced", Font.PLAIN, 50));

		// UPDATE BUTTON
		updateButton = new JButton(new ImageIcon("update.png"));
		updateButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		updateButton.addActionListener(e -> update());
		updateButton.setFont(new Font("Monospaced", Font.PLAIN, 10));
		updateButton.setHorizontalAlignment(JButton.RIGHT);

		// DELETE BUTTON
		deleteButton = new JButton(new ImageIcon("delete.png"));
		deleteButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		deleteButton.addActionListener(e -> delete(w));
		deleteButton.setFont(new Font("Monospaced", Font.PLAIN, 10));
		deleteButton.setHorizontalAlignment(JButton.RIGHT);

		// SHOW PANEL
		showPanel = new JPanel();
		showPanel.setLayout(new GridBagLayout());
		showPanel.setBorder(
				new CompoundBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.BLACK, 2)),
						new EmptyBorder(10, 10, 10, 10)));
		showPanel.setMinimumSize(new Dimension(300, 300));

		// adding components to show panel
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		showPanel.add(titleLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		showPanel.add(statusLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1.0;
		showPanel.add(genreLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1.0;
		showPanel.add(tagsLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 1.0;
		showPanel.add(ratingLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		c.gridx = 0;
		c.gridy = 6;
		showPanel.add(updateButton, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1.0;
		c.weightx = 0.0;
		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		c.gridx = 1;
		c.gridy = 6;
		showPanel.add(deleteButton, c);

		// adding show panel to the watchlist panel is corresponds with
		w.watchlistPanel.add(showPanel);
		w.watchlistPanel.revalidate();
		w.watchlistPanel.repaint();

		w.shows.add(this);

	}

	// GET METHODS

	public String[] getShow() {
		return show;
	}

	public JPanel getPanel() {
		return showPanel;
	}

	// SET METHODS
	public void setTitle(String t) {
		titleLabel.setText(t);
	}

	public void setStatus(String s) {
		statusLabel.setText(s);
	}

	public void setGenre(String g) {
		genreLabel.setText(g);
	}

	public void setTags(String t) {
		tagsLabel.setText(t);
	}

	public void setRating(String r) {
		ratingLabel.setText(r);
	}

	public void update() {

		JTextField title = new JTextField(show[0]);
		JComboBox<String> status = new JComboBox<String>(
				new String[] { "recommended", "planning to watch", "watching", "completed" });
		JTextField genre = new JTextField(show[2]);
		JTextField tags = new JTextField(show[3]);
		JTextField rating = new JTextField(show[4].equals("0") ? "" : show[4]);
		Object[] message = { "title: ", title, "status: ", status, "genre: ", genre, "tags: ", tags, "rating:",
				rating };

		int option = JOptionPane.showConfirmDialog(null, message, "update " + show[0], JOptionPane.OK_CANCEL_OPTION);

		String[] showInfo = new String[5];
		if (JOptionPane.OK_OPTION == option) {
			showInfo[0] = title.getText();
			showInfo[1] = (String) status.getSelectedItem();
			showInfo[2] = genre.getText();
			showInfo[3] = tags.getText();
			showInfo[4] = rating.getText();
			show = showInfo;
			setTitle(showInfo[0]);
			setStatus(showInfo[1]);
			setGenre(showInfo[2]);
			setTags(showInfo[3]);

			if (showInfo[4].isEmpty() || Integer.parseInt(showInfo[4]) == 0) {
				show[4] = "0";
				setRating(""); // stars(0)

			} else
				setRating(stars(Integer.parseInt(showInfo[4])));

		}

	}

	public void delete(Watchlist w) {
		int input = JOptionPane.showConfirmDialog(null, "are you sure you want to delete " + show[0] + "?", "∠(´•０•`)〴",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (input == JOptionPane.YES_OPTION) {
			// REMOVING SHOW FROMS SHOWS ARRAY LIST
			w.shows.remove(this);
			// REMOVING SHOW PANEL FROM WATCHLIST PANEL
			w.watchlistPanel.remove(showPanel);
			w.watchlistPanel.revalidate();
			w.watchlistPanel.repaint();
		}
	}

}
