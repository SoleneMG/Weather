package main.presentation;

import main.Inject;
import main.data.models.CityWeather;
import main.data.server.models.NetworkResponse;
import main.domain.ScreenLogic;
import main.presentation.utils.NumberFormatPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Screen extends JFrame {
    private final ScreenLogic logic = Inject.screenLogic();
    public JTextField cityField;
    public JTextArea cityWeather = new JTextArea("");

    public void build() {
        setTitle("Météo");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(true);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel());
        setVisible(true);
    }

    private JPanel mainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(jToolBar(), BorderLayout.NORTH);
        mainPanel.add(westComponent(), BorderLayout.WEST);
        mainPanel.add(centerComponent(), BorderLayout.CENTER);
        return mainPanel;
    }

    private JToolBar jToolBar() {
        return new JToolBar();
    }

    private JPanel westComponent() {
        JPanel westPanel = new JPanel(new FlowLayout());
        JTree tree = new JTree();
        JScrollPane scrollBar = new JScrollPane(tree);
        scrollBar.setPreferredSize(new Dimension(50, 0));
        westPanel.add(scrollBar);
        return westPanel;
    }

    private JPanel centerComponent() {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel city = new JLabel("Entrez la ville pour laquelle vous souhaitez connaitre la météo");
        city.setPreferredSize(new Dimension(400, 30));
        panel.add(city);
        cityField = new JTextField("City name", 20);
        panel.add(cityField);
        JButton validate = new JButton(clickOnValidateButton());
        validate.setText("Valider");
        panel.add(validate);
        panel.add(cityWeather);
        return panel;
    }

    private AbstractAction clickOnValidateButton() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cityName = cityField.getText();
                NetworkResponse<CityWeather> networkResponse = logic.getWeather(cityName);
                if (networkResponse.code == 200) {
                    String tempMin = NumberFormatPresentation.twoNumberAfterDecimalPoint(networkResponse.data.tempMin);
                    String tempMax = NumberFormatPresentation.twoNumberAfterDecimalPoint(networkResponse.data.tempMax);
                    cityWeather.setText("A " + cityName + ", la température min est de " + tempMin + "°C et la température max est de " + tempMax + "°C.");
                } else {
                    cityWeather.setText("Ville inconnue");
                }
            }
        };
    }


}
