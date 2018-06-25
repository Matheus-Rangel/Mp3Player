package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import mp3player.Music;
import mp3player.PlayerManager;
import userManagement.UserManager;

import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import java.awt.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class PlayerViews extends JFrame {

	private JPanel contentPane;
	private PlayerManager playerManager;
	private UserManager userManager;
	private JButton btnNext;
	private JButton btnPlay;
	private JButton btnStop;
	private JButton btnPrev;
	private JLabel lblCurrentMusic;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnUser;
	private JMenuItem mntmAddDirectory;
	private JMenuItem mntmAddUser;
	private JMenuItem mntmAddMusic;
	private JMenuItem mntmChangeUser;
	private JList allMusics;
	private JList currentPlayList;
	private JList playlists;
	private AddUserDialog addUserDiag;
	private JLabel lblMusicas;
	private JLabel lblPlaylist;
	private JLabel lblPlaylists;
	private JLabel lblUsername;
	
	private void initComponents() {
		addUserDiag = new AddUserDialog(userManager);
		allMusics = new JList(playerManager.allMusics.getMusics()); 
		currentPlayList = new JList(playerManager.allMusics.getMusics());
		playlists = new JList(playerManager.playlists);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(84, 290, 60, 25);
		contentPane.add(btnPlay);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(156, 290, 60, 25);
		contentPane.add(btnNext);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(84, 290, 60, 25);
		contentPane.add(btnStop);
		btnStop.setVisible(false);
		
		btnPrev = new JButton("Prev");
		btnPrev.setBounds(12, 290, 60, 25);
		contentPane.add(btnPrev);
		
		lblCurrentMusic = new JLabel(this.playerManager.getMusicName());
		lblCurrentMusic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurrentMusic.setBounds(228, 290, 250, 25);
		contentPane.add(lblCurrentMusic);
		
		JScrollPane scrollPaneAllMusics = new JScrollPane();
		scrollPaneAllMusics.setBounds(12, 61, 179, 217);
		contentPane.add(scrollPaneAllMusics);
		scrollPaneAllMusics.setViewportView(allMusics);
		
		JScrollPane scrollPanePlaylist = new JScrollPane();
		scrollPanePlaylist.setBounds(243, 61, 179, 217);
		contentPane.add(scrollPanePlaylist);
		scrollPanePlaylist.setViewportView(currentPlayList);
		
		menuBar= new JMenuBar();
		menuBar.setBounds(0, 0, 622, 26);
		contentPane.add(menuBar);
		
		mnFile = new JMenu("Menu");
		menuBar.add(mnFile);
		
		mntmAddDirectory = new JMenuItem("Add Directory");
		mnFile.add(mntmAddDirectory);
		
		mntmAddMusic = new JMenuItem("Add Music");
		mnFile.add(mntmAddMusic);
		
		mnUser = new JMenu("User");
		menuBar.add(mnUser);
		
		mntmAddUser = new JMenuItem("Add User");
		mnUser.add(mntmAddUser);
		
		mntmChangeUser = new JMenuItem("Change User");
		mnUser.add(mntmChangeUser);
		
		JScrollPane scrollPanePlaylists = new JScrollPane();
		scrollPanePlaylists.setBounds(467, 153, 141, 120);
		contentPane.add(scrollPanePlaylists);
		scrollPanePlaylists.setViewportView(playlists);
		
		lblMusicas = new JLabel("Musicas:");
		lblMusicas.setBounds(12, 43, 56, 16);
		contentPane.add(lblMusicas);
		
		lblPlaylist = new JLabel("Playlist:");
		lblPlaylist.setBounds(243, 43, 56, 16);
		contentPane.add(lblPlaylist);
		
		lblPlaylists = new JLabel("Playlists:");
		lblPlaylists.setBounds(467, 135, 56, 16);
		contentPane.add(lblPlaylists);
		
		lblUsername = new JLabel("username");
		lblUsername.setBounds(467, 43, 141, 25);
		contentPane.add(lblUsername);
		
	}
	private void createEvents() {
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnPlay.setVisible(false);
				btnStop.setVisible(true);
				playerManager.play();
				lblCurrentMusic.setText(playerManager.getMusicName());
			}
		});
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnPlay.setVisible(true);
				btnStop.setVisible(false);
				playerManager.stop();
			}
		});
		
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playerManager.next();
				lblCurrentMusic.setText(playerManager.getMusicName());
			}
		});
		
		btnPrev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playerManager.prev();
				lblCurrentMusic.setText(playerManager.getMusicName());
			}
		});
		
		mntmAddDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.showOpenDialog(null);
				playerManager.addDirectory(fc.getSelectedFile());
			}
		});
		
		mntmAddMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser fc = new JFileChooser();
					fc.setFileFilter(new FileFilter() {
						
						@Override
						public String getDescription() {
							return "MP3 musics";
						}
						
						@Override
						public boolean accept(File f) {
							return f.getName().endsWith(".mp3") || f.isDirectory();
					    }
					});
					
					fc.showOpenDialog(null);
					playerManager.addMusic(fc.getSelectedFile());
			}
		});
		mntmAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserDiag.setVisible(true);
			}
		});
		mntmChangeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		currentPlayList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerManager.stop();
				playerManager.setCurrentMusic(currentPlayList.getSelectedIndex());
				playerManager.play();
				lblCurrentMusic.setText(playerManager.getMusicName());
				btnPlay.setVisible(false);
				btnStop.setVisible(true);
			}
		});
	
	}
	
	public PlayerViews(PlayerManager p, UserManager u) {
		this.playerManager = p;
		this.userManager = u;
		initComponents();
		createEvents();
	}
}
