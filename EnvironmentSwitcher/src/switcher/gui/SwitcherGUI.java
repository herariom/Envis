package switcher.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private DefaultListModel<String> args = new DefaultListModel<>();

    private EnvironmentManager envMgr;
    private JTextField appFilepath;
    private JTextField appName;
    private JTextField fieldFilePath;
    private JTextField fieldName;
    private JTextField fieldEnvName;
    private JTextField fieldJsonPath;
    private JTextField fieldSavePath;
    private JTextField fieldArg;

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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        frmEnvironmentSwitcher = new JFrame();
        frmEnvironmentSwitcher.setTitle("Environment Switcher");
        frmEnvironmentSwitcher.setBounds(100, 100, 1008, 320);
        frmEnvironmentSwitcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmEnvironmentSwitcher.getContentPane().setLayout(null);

        JList<Environment> envList = new JList<>(environments);
        envList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        envList.setBounds(10, 30, 165, 186);
        frmEnvironmentSwitcher.getContentPane().add(envList);

        JLabel lblEnvironments = new JLabel("Environments");
        lblEnvironments.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblEnvironments.setBounds(10, 7, 165, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblEnvironments);

        JList<App> appList = new JList<>(apps);
        appList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        appList.setBounds(352, 30, 165, 186);
        frmEnvironmentSwitcher.getContentPane().add(appList);

        JLabel lblApps = new JLabel("Apps");
        lblApps.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblApps.setBounds(342, 7, 165, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblApps);

        JButton btnStartApps = new JButton("Start Apps");

        btnStartApps.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnStartApps.setBounds(641, 251, 165, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnStartApps);

        JButton btnEndApps = new JButton("End Apps");

        btnEndApps.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnEndApps.setBounds(816, 251, 165, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnEndApps);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblName.setBounds(517, 31, 45, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblName);

        fieldFilePath = new JTextField();
        fieldFilePath.setBounds(572, 52, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldFilePath);
        fieldFilePath.setColumns(10);

        JLabel lblFilepath = new JLabel("Filepath:");
        lblFilepath.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblFilepath.setBounds(517, 54, 45, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblFilepath);

        fieldName = new JTextField();
        fieldName.setBounds(572, 28, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldName);
        fieldName.setColumns(10);

        JButton btnAddApp = new JButton("Add App");

        btnAddApp.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAddApp.setBounds(517, 115, 151, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnAddApp);

        JButton btnRemoveApp = new JButton("Remove App");

        btnRemoveApp.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRemoveApp.setBounds(517, 146, 151, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnRemoveApp);
        
        fieldEnvName = new JTextField();
        fieldEnvName.setBounds(240, 30, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldEnvName);
        fieldEnvName.setColumns(10);
        
        JLabel lblName_1 = new JLabel("Name:");
        lblName_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblName_1.setBounds(185, 32, 45, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblName_1);
        
        JButton btnAddEnv = new JButton("Add Env");

        btnAddEnv.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAddEnv.setBounds(185, 61, 151, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnAddEnv);
        
        JButton btnRemoveEnv = new JButton("Remove Env");
        
        btnRemoveEnv.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRemoveEnv.setBounds(185, 92, 151, 21);
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
        btnLoadJson.setBounds(482, 252, 102, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnLoadJson);
        
        JLabel lblJsonPath = new JLabel("JSON Path:");
        lblJsonPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblJsonPath.setBounds(303, 255, 63, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblJsonPath);
        
        fieldJsonPath = new JTextField();
        fieldJsonPath.setBounds(376, 253, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldJsonPath);
        fieldJsonPath.setColumns(10);
        
        JLabel lblSavePath = new JLabel("Save path:");
        lblSavePath.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSavePath.setBounds(10, 255, 72, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblSavePath);
        
        fieldSavePath = new JTextField();
        fieldSavePath.setBounds(83, 253, 96, 19);
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
        btnSave.setBounds(191, 252, 102, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnSave);
        
        JButton btnChooseFile = new JButton("Choose file");
        btnChooseFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Executables", "exe");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(frmEnvironmentSwitcher);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                   fieldFilePath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        btnChooseFile.setBounds(517, 81, 149, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnChooseFile);
        
        JList<String> argList = new JList<>(args);
        argList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        argList.setBounds(679, 30, 165, 186);
        
        appList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting() && envList.getSelectedIndex() >= 0) {
                    System.out.println(envList.getSelectedIndex());
                    App app = apps.get(appList.getSelectedIndex());
                    
                    if (app != null) {
                        args.clear();
                        args.addAll(app.getArguments());
                    }
                }
            }
        });
        
        frmEnvironmentSwitcher.getContentPane().add(argList);
        
        JButton btnAddArg = new JButton("Add Arg");
        btnAddArg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Environment env = environments.get(envList.getSelectedIndex());
                
                if (env != null) {
                    App app = apps.get(appList.getSelectedIndex());
                    app.addArgument(fieldArg.getText());
                    
                    System.out.println(app.getArguments());
                    
                    args.clear();
                    args.addAll(app.getArguments());
                }
            }
        });
        btnAddArg.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAddArg.setBounds(854, 61, 127, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnAddArg);
        
        JButton btnRemoveArg = new JButton("Remove Arg");
        btnRemoveArg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Environment env = environments.get(envList.getSelectedIndex());
                    
                    if (env != null) {
                        App app = apps.get(appList.getSelectedIndex());
                        app.removeArgument(argList.getSelectedIndex());
                        
                        args.clear();
                        args.addAll(app.getArguments());
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnRemoveArg.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRemoveArg.setBounds(854, 92, 127, 21);
        frmEnvironmentSwitcher.getContentPane().add(btnRemoveArg);
        
        fieldArg = new JTextField();
        fieldArg.setBounds(885, 30, 96, 19);
        frmEnvironmentSwitcher.getContentPane().add(fieldArg);
        fieldArg.setColumns(10);
        
        JLabel lblArg = new JLabel("Arg:");
        lblArg.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblArg.setBounds(854, 32, 57, 13);
        frmEnvironmentSwitcher.getContentPane().add(lblArg);

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
