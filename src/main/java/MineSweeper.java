import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MineSweeper extends Application {
    private Stage nowStage;
    private final int displayHeight= 1000;
    private final int displayWidth = 1000;

    @Override
    public void start(Stage primaryStage) {
        executionApplication(primaryStage);
    }

    void executionApplication(Stage continuationStage) {
        nowStage = continuationStage;
        Scene scene = new Scene(generateMineSweeperField());
        nowStage.setTitle("マインスイーパー");
        nowStage.setScene(scene);
        nowStage.show();
    }

    private Parent generateMineSweeperField() {
        GenerateDialog initialSettingDialog = new GenerateDialog();
        int vertical = initialSettingDialog.InputNumberDialog("縦の長さを入力してください。(1 ~ 400)", "フィールドの設定", 1, 400);
        System.out.println(vertical); // debug
        int width = initialSettingDialog.InputNumberDialog("横の長さを入力してください。(1 ~ 400)", "フィールドの設定", 1, 400);
        System.out.println(width);
        int numberOfBombs = initialSettingDialog.InputNumberDialog("爆弾の個数を入力してください。(1 ~ " + vertical * width + ")", "爆弾の設定", 1, vertical * width);
        System.out.println(numberOfBombs);
        Pane displayLayout = new Pane();
        displayLayout.setPrefSize(displayWidth, displayHeight);
        FieldCreation fieldCreation = new FieldCreation();

        // displayHeight x displayWidth fit the fields
        fieldCreation.rectangleLength = (double) Math.min(displayHeight, displayWidth) / (double)Math.max(vertical, width);
        System.out.println(fieldCreation.rectangleLength);

        fieldCreation.fieldInitialization(nowStage, this, vertical, width, numberOfBombs);
        fieldCreation.AddTileToPane(vertical, width, displayLayout);
        return displayLayout;
    }
}
