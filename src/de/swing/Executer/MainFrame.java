package de.swing.Executer;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class MainFrame extends JFrame {

    private JLabel countLabel1 = new JLabel("0");
    private JLabel statusLabel = new JLabel("Task not completed.");
    private JButton startButton = new JButton("Start");

    public MainFrame(String title) {
        super(title);

        setLayout(new GridBagLayout());

        countLabel1.setFont(new Font("serif", Font.BOLD, 28));

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        add(countLabel1, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        add(statusLabel, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        add(startButton, gc);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                start();
            }
        });

        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void start1() {
        Thread worker = new Thread() {
            public void run() {
                for(int i=0; i<=10; i++) {
                    // Bad practice
                    countLabel1.setText(Integer.toString(i));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }
                // Bad practice
                statusLabel.setText("Completed.");
            }
        };

        worker.start();

    }

    private void start2() {
        Thread worker = new Thread() {
            public void run() {
                for(int i=0; i<=10; i++) {
                    final int count = i;
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            countLabel1.setText(Integer.toString(count));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        statusLabel.setText("Completed.");
                    }
                });

            }
        };

        worker.start();
    }

    private void start() {
        SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(1000);
                    publish(i);
                }
                return true;
            }
            protected void done() {
                boolean status;
                try {
                    status = get();
                    statusLabel.setText("Completed with status: " + status);
                } catch (InterruptedException e) {
                } catch (ExecutionException e) {
                }
            }

            @Override
            protected void process(List<Integer> chunks) {
                //Here we receive the values that we publish().
                int mostRecentValue = chunks.get(chunks.size()-1);
                countLabel1.setText(Integer.toString(mostRecentValue));
            }


        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame("SwingWorker Demo");
            }
        });
    }
}
