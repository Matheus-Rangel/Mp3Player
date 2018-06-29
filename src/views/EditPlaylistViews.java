package views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mp3player.Music;
import mp3player.PlayerManager;
import mp3player.Playlist;

public class EditPlaylistViews extends JFrame {

	private JPanel contentPane;
	private JTextField textFielPlName;
	private PlayerManager playerManager;
	private JButton btnNewPlaylist;
	private JButton btnRemovePlaylist;
	private JList<Playlist> playlists;
	private JList<Music> allMusics;
	private JList<Music> plMusics;
	private JButton btnAddMusic;
	private JButton btnRemoveMusic;
	private JButton btnSwap;
	private JScrollPane scrollPanePlaylists;
	private JScrollPane scrollPaneAllMusics;
	private JScrollPane scrollPanePlMusics;
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 562, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPanePlaylists = new JScrollPane();
		scrollPanePlaylists.setBounds(12, 12, 132, 204);
		contentPane.add(scrollPanePlaylists);
		
		playlists = new JList<Playlist>(playerManager.playlists);
		
		scrollPanePlaylists.setViewportView(playlists);
		
		btnNewPlaylist = new JButton("New Playlist");
		btnNewPlaylist.setBounds(12, 229, 132, 26);
		contentPane.add(btnNewPlaylist);
		
		btnRemovePlaylist = new JButton("Remove Playlist");
		btnRemovePlaylist.setBounds(12, 268, 132, 26);
		contentPane.add(btnRemovePlaylist);
		
		scrollPaneAllMusics = new JScrollPane();
		scrollPaneAllMusics.setBounds(199, 38, 132, 218);
		contentPane.add(scrollPaneAllMusics);
		
		allMusics = new JList<Music>(playerManager.allMusics.getMusics());
		scrollPaneAllMusics.setViewportView(allMusics);
		
		scrollPanePlMusics = new JScrollPane();
		scrollPanePlMusics.setBounds(380, 38, 132, 218);
		contentPane.add(scrollPanePlMusics);
		
		plMusics = new JList<>();
		scrollPanePlMusics.setViewportView(plMusics);
		
		JLabel lblAllMusics = new JLabel("All Musics:");
		lblAllMusics.setBounds(200, 9, 73, 16);
		contentPane.add(lblAllMusics);
		
		btnAddMusic = new JButton("Add Selected");
		btnAddMusic.setBounds(199, 269, 132, 25);
		contentPane.add(btnAddMusic);
		
		btnRemoveMusic = new JButton("Remove Selected");
		btnRemoveMusic.setBounds(380, 269, 132, 25);
		contentPane.add(btnRemoveMusic);
		
		btnSwap = new JButton("Swap Selected");
		btnSwap.setBounds(380, 307, 132, 25);
		contentPane.add(btnSwap);
		
		textFielPlName = new JTextField();
		textFielPlName.setBounds(380, 12, 132, 22);
		contentPane.add(textFielPlName);
		textFielPlName.setColumns(10);
		
		JLabel lblPlaylist = new JLabel("Playlist:");
		lblPlaylist.setBounds(324, 12, 44, 16);
		contentPane.add(lblPlaylist);
	}
	
	private void createEvents() {
		btnNewPlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerManager.playlists.addElement(new Playlist("New Playlist"));
			}
		});
		
		btnRemovePlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerManager.playlists.removeElementAt(playlists.getSelectedIndex());
			}
		});
		
		btnAddMusic.addMouseListener(new MouseAdapter() {
			private ArrayList<Music> m;
			private Playlist p;
			@Override
			public void mouseReleased(MouseEvent e) {
				m =  new ArrayList<Music>(allMusics.getSelectedValuesList());
				p = playlists.getSelectedValue();
				for (Music music : m) {
					System.out.println(music.toString());
					p.getMusics().addElement(music);
				}
				plMusics.setModel(p.getMusics());
			}
		});
		
		btnRemoveMusic.addMouseListener(new MouseAdapter() {
			private int[] indeces;
			private Playlist p;
			@Override
			public void mouseReleased(MouseEvent arg0) {
				indeces = playlists.getSelectedIndices();
				p = playlists.getSelectedValue();
				for (int index : indeces) {
					p.getMusics().removeElementAt(index);
				}
			}
		});
		
		btnSwap.addMouseListener(new MouseAdapter() {
			private int[] indeces;
			private Playlist p;
			@Override
			public void mouseReleased(MouseEvent e) {
				indeces = allMusics.getSelectedIndices();
				p = playlists.getSelectedValue();
				Music m1 = p.getMusics().getElementAt(indeces[1]);
				Music m2 = p.getMusics().getElementAt(indeces[2]);
				p.getMusics().setElementAt(m2, indeces[1]);
				p.getMusics().setElementAt(m1, indeces[2]);
			}
		});
		
		playlists.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				plMusics = new JList<Music>(playlists.getSelectedValue().getMusics());
				textFielPlName.setText(playlists.getSelectedValue().toString());
			}
		});
		
		textFielPlName.addPropertyChangeListener(new PropertyChangeListener() {
			private Playlist p;
			public void propertyChange(PropertyChangeEvent arg0) {
				p = playlists.getSelectedValue();
				p.setName(textFielPlName.getText());
			}
		});
	}
	public EditPlaylistViews(PlayerManager pm) {
		this.playerManager = pm;
		initComponents();
		createEvents();
	}
}
