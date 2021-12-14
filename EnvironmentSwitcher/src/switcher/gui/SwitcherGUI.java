package switcher.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import switcher.App;
import switcher.Environment;
import switcher.EnvironmentManager;
import switcher.Loader;
import switcher.WindowsApp;

public class SwitcherGUI {

    private JFrame frmEnvironmentSwitcher;
    private JTextField envName;
    private DefaultListModel<Environment> environments = new DefaultListModel<>();
    private DefaultListModel<App> apps = new DefaultListModel<>();

    private EnvironmentManager envMgr;
    private JTextField appFilepath;
    private JTextField appName;
    private JTextField fieldFilePath;
    private JTextField fieldName;
    private JTextField fieldEnvName;
    private JTextField fieldJsonPath;
    private JTextField fieldSavePath;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwitcherGUI window = new SwitcherGUI();
                    window.frmEnvironmentSwitcher.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SwitcherGUI() {
        envMgr = EnvironmentManager.getInstance();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmEnvironmentSwitcher = new JFrame();
        frmEnvironmentSwitcher.setTitle("Environment Switcher");
        frmEnvironmentSwitcher.setBounds(100, 100, 560, 367);
        frmEnvironmentSwitcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmEnvironmentSwitcher.getContentPane().setLayout(null);

        JList<Environment> envList = new JList<>(environments);
        envList.setBounds(10, 30, 165, 186);
        frmEnvironmentSwitcher.getContentPane().add(envList);

        JLabel lblEnvironments = new JLabel("Environments");
        lblEnvironments.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblEnvironments.setBounds(10, 7, 165, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblEnvironments);

        JList<App> appList = new JList<>(apps);
        appList.setBounds(212, 30, 165, 186);
        frmEnvironmentSwitcher.getContentPane().add(appList);

        JLabel lblApps = new JLabel("Apps");
        lblApps.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblApps.setBounds(212, 7, 165, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblApps);

        JButton btnStartApps = new JButton("Start Apps");

        btnStartApps.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnStartApps.setBounds(373, 245, 165, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnStartApps);

        JButton btnEndApps = new JButton("End Apps");

        btnEndApps.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnEndApps.setBounds(373, 276, 165, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnEndApps);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblName.setBounds(387, 31, 45, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblName);

        fieldFilePath = new JTextField();
        fieldFilePath.setBounds(442, 52, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldFilePath);
        fieldFilePath.setColumns(10);

        JLabel lblFilepath = new JLabel("Filepath:");
        lblFilepath.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblFilepath.setBounds(387, 54, 45, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblFilepath);

        fieldName = new JTextField();
        fieldName.setBounds(442, 28, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldName);
        fieldName.setColumns(10);

        JButton btnAddApp = new JButton("Add App");

        btnAddApp.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAddApp.setBounds(387, 81, 151, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnAddApp);

        JButton btnRemoveApp = new JButton("Remove App");

        btnRemoveApp.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRemoveApp.setBounds(387, 112, 151, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnRemoveApp);
        
        fieldEnvName = new JTextField();
        fieldEnvName.setBounds(65, 224, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldEnvName);
        fieldEnvName.setColumns(10);
        
        JLabel lblName_1 = new JLabel("Name:");
        lblName_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblName_1.setBounds(10, 226, 45, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblName_1);
        
        JButton btnAddEnv = new JButton("Add Env");

        btnAddEnv.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAddEnv.setBounds(10, 246, 151, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnAddEnv);
        
        JButton btnRemoveEnv = new JButton("Remove Env");
        
        btnRemoveEnv.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRemoveEnv.setBounds(163, 245, 151, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnRemoveEnv);
        
        JButton btnLoadJson = new JButton("Load JSON");
        btnLoadJson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    envMgr.addEnvironment(Loader.loadEnvironment(fieldJsonPath.getText()));

                    environments.clear();
                    environments.addAll(envMgr.getEnvironments().values());
                } catch (JsonParseException e1) {
                    e1.printStackTrace();
                } catch (JsonMappingException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnLoadJson.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLoadJson.setBounds(191, 278, 102, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnLoadJson);
        
        JLabel lblJsonPath = new JLabel("JSON Path:");
        lblJsonPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblJsonPath.setBounds(10, 280, 63, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblJsonPath);
        
        fieldJsonPath = new JTextField();
        fieldJsonPath.setBounds(83, 278, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldJsonPath);
        fieldJsonPath.setColumns(10);
        
        JLabel lblSavePath = new JLabel("Save path:");
        lblSavePath.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSavePath.setBounds(10, 307, 72, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblSavePath);
        
        fieldSavePath = new JTextField();
        fieldSavePath.setBounds(83, 305, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldSavePath);
        fieldSavePath.setColumns(10);
        
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Environment env = environments.get(envList.getSelectedIndex());
                
                if (env != null) {
                    Loader.saveEnvironment(env);
                }
            }
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnSave.setBounds(191, 304, 102, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnSave);

        envList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting() && envList.getSelectedIndex() >= 0) {
                    System.out.println(envList.getSelectedIndex());
                    Environment env = environments.get(envList.getSelectedIndex());
                    
                    if (env != null) {
                        apps.clear();
                        apps.addAll(env.getApps());
                    }
                }
            }
        });
        
        btnAddEnv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                envMgr.addEnvironment(fieldEnvName.getText());
                
                environments.clear();
                environments.addAll(envMgr.getEnvironments().values());
            }
        });
        
        btnRemoveEnv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Environment env = environments.get(envList.getSelectedIndex());
                    
                    if (env != null) {
                        envMgr.removeEnvironment(env.getName());
                    }
                    
                    environments.clear();
                    environments.addAll(envMgr.getEnvironments().values());
                } catch (ArrayIndexOutOfBoundsException exception) {
                    // User hasn't selected an environment
                    
                    exception.printStackTrace();
                }
            }
        });
        
        btnStartApps.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Environment env = environments.get(envList.getSelectedIndex());

                if (env != null) {
                    try {
                        env.startApps();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        btnEndApps.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Environment env = environments.get(envList.getSelectedIndex());

                if (env != null) {
                    env.closeApps();
                }
            }
        });

        btnAddApp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Environment env = environments.get(envList.getSelectedIndex());
                
                if (env != null) {
                    WindowsApp app = new WindowsApp(fieldFilePath.getText(), fieldName.getText());
                    
                    env.addApp(app);
                    
                    apps.clear();
                    apps.addAll(env.getApps());
                }
            }
        });

        btnRemoveApp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Environment env = environments.get(envList.getSelectedIndex());
                    
                    if (env != null) {
                        App toRemove = apps.get(appList.getSelectedIndex());
                        
                        if (toRemove != null) {
                            env.removeApp(toRemove);
                            
                            apps.clear();
                            apps.addAll(env.getApps());
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    // User hasn't selected an app
                    
                    exception.printStackTrace();
                }
            }
        });
    }
}
