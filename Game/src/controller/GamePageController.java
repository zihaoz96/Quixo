package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GamePageController implements Initializable {
	// Un plateau de jeu
	@FXML
	public GridPane grid;

	// Tous les buttons sur plateforme
	@FXML
	public Button newGame;

	// TextArea
	@FXML
	public TextArea progress;

	// Taille de plateau
	final int N = 5;
	
	/* Avec 3 etape: 
	 * etape1 select case
	 * etape2 select direction
	 * etape3 AI prend son tour
	 * observer le plateau clique
	 */
	private Queue<Integer> queue = new LinkedList<Integer>();
	
	ArrayList<Node> directionPossible;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		progress.setText("The game starts now...\n");
		queue.add(0);
	}

	@FXML
	public void replay(ActionEvent event) {
		progress.setText("The game starts now...\n");
		resetGridPane();
	}

	@FXML
	public void clickBoard(MouseEvent event) {
		if (!queue.isEmpty()) {
			int step = queue.poll();
			if (step == 0) {
				step1Click(event);
				queue.add(1);
			} else if (step == 1) {
				step2Click(event);
			}
		}
	}
	
	private void step1Click(MouseEvent event) {
		directionPossible = new ArrayList<Node>();
				
		Node clickedNode = event.getPickResult().getIntersectedNode();
		if (clickedNode != grid) {
			// Clique sur le node dependant
			Integer colIndex = GridPane.getColumnIndex(clickedNode); // j
			Integer rowIndex = GridPane.getRowIndex(clickedNode); // i
			System.out.println("Mouse clicked cell: " + rowIndex + "," + colIndex);
			
			// Prend stackPane qu'on clique
			StackPane stackPane = (StackPane) grid.getChildren().get(rowIndex*N+colIndex);
			if (stackPane.getChildren().isEmpty()) {
				Label label = new Label("X");
				label.setFont(Font.font("Frank Ruehl CLM", 30));
				label.setDisable(true);
				stackPane.getChildren().add(label);
			}
			
			
			if (rowIndex == 0) { 
				showDirectionPossible((StackPane)
				grid.getChildren().get(N*(N-1)+colIndex));
			 
			 	if (colIndex != 0 && colIndex != N-1 ) { 
			 		showDirectionPossible((StackPane) grid.getChildren().get(0)); 
			 		showDirectionPossible((StackPane) grid.getChildren().get(N-1)); 
	 			}
	 		}
			 
			if (rowIndex == N-1) { 
				showDirectionPossible((StackPane)
				grid.getChildren().get(colIndex)); 
				if (colIndex != 0 && colIndex != N-1 ) {
					showDirectionPossible((StackPane) grid.getChildren().get(N*(N-1)));
					showDirectionPossible((StackPane) grid.getChildren().get(N*N-1));
				} 
			}
			 
			if (colIndex == 0) { 
				showDirectionPossible((StackPane)
				grid.getChildren().get(rowIndex*N+N-1)); 
				if (rowIndex != 0 && rowIndex != N-1) { 
					showDirectionPossible((StackPane) grid.getChildren().get(0));
					showDirectionPossible((StackPane) grid.getChildren().get(N*(N-1))); 
				} 
			}
			 
			if (colIndex == N-1) { 
				showDirectionPossible((StackPane) grid.getChildren().get(rowIndex*N)); 
				if (rowIndex != 0 && rowIndex != N-1 ) {
					showDirectionPossible((StackPane) grid.getChildren().get(N-1));
					showDirectionPossible((StackPane) grid.getChildren().get(N*N-1)); 
				} 
			}
			progress.appendText("Choose one direction..\n");
		}
	}
	
	private void step2Click(MouseEvent event) {
		Node clickedNode = event.getPickResult().getIntersectedNode();
		if (clickedNode != grid) {
			Node directionNode = checkDirection(clickedNode);
			if (directionNode != null) {
				moveToDirection(directionNode);
				progress.appendText("You have choice! \n");
				
			}else {
				progress.appendText("You have to choose one cell green for the direction !\n");
				queue.add(1);
			}
		}
	}
	
	private void moveToDirection(Node clickedNode) {
		Integer colIndex = GridPane.getColumnIndex(clickedNode); // j
		Integer rowIndex = GridPane.getRowIndex(clickedNode); // i
	}
	
	private Node checkDirection(Node clickedNode) {
		for (int i = 0; i < directionPossible.size(); i++) {
			if (directionPossible.get(i).equals(clickedNode)) {
				resetCellDirectionColor();
				return directionPossible.get(i);
			}
		}
		return null;
	}
	
	private void resetCellDirectionColor() {
		for (int i = 0; i < directionPossible.size(); i++) {
			StackPane stackPane = (StackPane) directionPossible.get(i);
			stackPane.setBackground(new Background(new BackgroundFill(Color.web("#ECEDEE"), CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}

	private void showDirectionPossible(StackPane stackPane) {
		stackPane.setBackground(new Background(new BackgroundFill(Color.web("#73F4BD"), CornerRadii.EMPTY, Insets.EMPTY)));
		directionPossible.add(stackPane);
	}

	private void resetGridPane() {
		StackPane stackPane;
		for (int i = 0; i < N * N; i++) {
			stackPane = (StackPane) grid.getChildren().get(i);
			stackPane.getChildren().clear();
		}
		
		resetCellDirectionColor();
		queue.clear();
		queue.add(0);
	}
}
