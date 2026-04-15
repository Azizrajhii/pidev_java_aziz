package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.MatchGame;
import tn.esprit.services.ServiceMatchGame;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class AjouterMatchGameController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private TextField dateMatchField;
    @FXML
    private TextField score1Field;
    @FXML
    private TextField score2Field;
    @FXML
    private TextField statutField;
    @FXML
    private TextField equipe1Field;
    @FXML
    private TextField equipe2Field;
    @FXML
    private TextField tournoiField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button submitBtn;

    private final ServiceMatchGame serviceMatchGame = new ServiceMatchGame();
    private MatchGame matchToEdit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (dateMatchField.getText() == null || dateMatchField.getText().isBlank()) {
            dateMatchField.setText(LocalDateTime.now().withSecond(0).withNano(0).toString().replace('T', ' '));
        }
    }

    public void setMatchToEdit(MatchGame match) {
        this.matchToEdit = match;
        titleLabel.setText("Modifier match");
        submitBtn.setText("Modifier");

        if (match.getDateMatch() != null) {
            dateMatchField.setText(match.getDateMatch().toLocalDateTime().withNano(0).toString().replace('T', ' '));
        }
        score1Field.setText(match.getScoreTeam1() == null ? "" : String.valueOf(match.getScoreTeam1()));
        score2Field.setText(match.getScoreTeam2() == null ? "" : String.valueOf(match.getScoreTeam2()));
        statutField.setText(match.getStatut() == null ? "" : match.getStatut());
        equipe1Field.setText(String.valueOf(match.getEquipe1Id()));
        equipe2Field.setText(String.valueOf(match.getEquipe2Id()));
        tournoiField.setText(String.valueOf(match.getTournoiId()));
    }

    @FXML
    private void handleSubmit() {
        try {
            Timestamp dateMatch = parseTimestamp(dateMatchField.getText());
            Integer score1 = parseNullableInt(score1Field.getText());
            Integer score2 = parseNullableInt(score2Field.getText());
            String statut = emptyToNull(statutField.getText());
            int equipe1 = parseRequiredInt(equipe1Field.getText(), "Equipe 1");
            int equipe2 = parseRequiredInt(equipe2Field.getText(), "Equipe 2");
            int tournoi = parseRequiredInt(tournoiField.getText(), "Tournoi");

            if (matchToEdit == null) {
                MatchGame match = new MatchGame(dateMatch, score1, score2, statut, equipe1, equipe2, tournoi);
                serviceMatchGame.ajouter(match);
                showSuccess("Match ajoute avec succes.");
            } else {
                matchToEdit.setDateMatch(dateMatch);
                matchToEdit.setScoreTeam1(score1);
                matchToEdit.setScoreTeam2(score2);
                matchToEdit.setStatut(statut);
                matchToEdit.setEquipe1Id(equipe1);
                matchToEdit.setEquipe2Id(equipe2);
                matchToEdit.setTournoiId(tournoi);
                serviceMatchGame.modifier(matchToEdit);
                showSuccess("Match modifie avec succes.");
            }

            closeWindow();
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        } catch (SQLException e) {
            showError("Erreur SQL : " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private Timestamp parseTimestamp(String raw) {
        String value = raw == null ? "" : raw.trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Date match obligatoire (yyyy-MM-dd HH:mm:ss).");
        }

        String normalized = value.replace('T', ' ');
        try {
            LocalDateTime dt = LocalDateTime.parse(normalized, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return Timestamp.valueOf(dt);
        } catch (DateTimeParseException e) {
            try {
                LocalDateTime dt = LocalDateTime.parse(value);
                return Timestamp.valueOf(dt);
            } catch (DateTimeParseException ignored) {
                throw new IllegalArgumentException("Format date invalide. Ex: 2026-04-16 18:30:00");
            }
        }
    }

    private int parseRequiredInt(String raw, String fieldName) {
        try {
            return Integer.parseInt(raw.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException(fieldName + " doit etre un entier.");
        }
    }

    private Integer parseNullableInt(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(raw.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Le score doit etre un entier.");
        }
    }

    private String emptyToNull(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value.trim();
    }

    private void closeWindow() {
        Stage stage = (Stage) dateMatchField.getScene().getWindow();
        stage.close();
    }

    private void showError(String message) {
        messageLabel.setStyle("-fx-text-fill: #e74c3c;");
        messageLabel.setText(message);
    }

    private void showSuccess(String message) {
        messageLabel.setStyle("-fx-text-fill: #27ae60;");
        messageLabel.setText(message);
    }
}
