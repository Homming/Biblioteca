package javafx.controller;

import dao.AluguelDAO;
import database.Database;
import database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class FXMLAnchorPaneGraficosAlugueisPorMesController implements Initializable {

    //Componentes da view
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis ctgAxis;
    @FXML
    private NumberAxis nmbAxis;

    private ObservableList<String> observableListMeses = FXCollections.observableArrayList();

    //BD
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final AluguelDAO aluguelDAO = new AluguelDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] arrayMeses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
        //Converte em lista e adiciona na observablelistMeses
        observableListMeses.addAll(Arrays.asList(arrayMeses));
        //Nomes do meses para o eixo horizontal
        ctgAxis.setCategories(observableListMeses);//setando a lista de meses como categorias do barChart
        aluguelDAO.setConnection(connection);//Conexao para obter as informações a serem exibidas
        Map<Integer, ArrayList> dados = aluguelDAO.listarAlugueisPorMes();
        //for each para varrer todo o map retornado pelo DAO
        for (Map.Entry<Integer, ArrayList> dadosItem : dados.entrySet()) {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();//nova serie de dados p cada elemento do map ex.: 2017,2018
            series.setName(dadosItem.getKey().toString());// key "2017"
            for (int i = 0; i < dadosItem.getValue().size(); i += 2) {//inclusao dos dados na serie
                String mes;
                Integer qtd;
                mes = retornaNomeMes((int) dadosItem.getValue().get(i));//pega o mes do banco em int e converte para String
                qtd = (Integer) dadosItem.getValue().get(i + 1);
                series.getData().add(new XYChart.Data<>(mes, qtd));//inclui os dados na serie
            }//fim FOR
            barChart.getData().add(series);//carrega as series
        }// fim FOR
    }//fim initialize
    
    public String retornaNomeMes(int mes) {
        switch (mes) {
            case 1:
                return "Jan";
            case 2:
                return "Fev";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "Mai";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Set";
            case 10:
                return "Out";
            case 11:
                return "Nov";
            case 12:
                return "Dez";
            default:
                return "";
        }
    }

}
