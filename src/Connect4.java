// JFC
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

// GTGE
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.object.sprite.*;
import com.golden.gamedev.object.collision.*;
import java.util.ArrayList;


/**
 * It's time to play!
 *
 * Objective: show how to use playfield to automate all things!
 */
public class Connect4 extends Game {


    PlayField        playfield;         // the game playfield
    Background       background;
    SpriteGroup      boardgroup;
    SpriteGroup      arrowgroup;
    SpriteGroup      chipgroup;
    SpriteGroup      winLoseGroup;
    Sprite board;
    Sprite arrow;
    Sprite chip;
    Sprite msg;
    int activeflag=0;
    int dropflag=0;
    int turn;
    int[][] filledMatrix={{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0}};
    int pos = 0;
    int level;
    int [][]tab={ {0,0,0,0,0,0,0},
                  {0,0,0,0,0,0,0},
                  {0,0,0,0,0,0,0},
                  {0,0,0,0,0,0,0},
                  {0,0,0,0,0,0,0},
                  {0,0,0,0,0,0,0}};
    Nodo padre=new Nodo(tab,true);


 /****************************************************************************/
 /**************************** GAME SKELETON *********************************/
 /****************************************************************************/
    public int getMove(int[][] board1, int[][] board2){
        for(int i=0; i<6;i++){
            for(int j=0; j<7; j++){
                if(board1[i][j]!=board2[i][j]){
                    return j;
                }
            }
        }
        return -1;
    }
    public boolean boardFull(){
        for(int i=0; i<6;i++){
            for(int j=0; j<7; j++){
                if(filledMatrix[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }
    public int insertInMatrix(){
        int count=0;
        for(int i = 5; i>=0; i-- ){
            if(!boardFull()){
            if(filledMatrix[i][pos]==0){
                filledMatrix[i][pos]=1;
                return count;
            }count++;
            }
        }
        return -1;
    }
    public int playerMove(){
        int count=0;
        for(int i = 5; i>=0; i-- ){
            if(padre.matriz[i][pos]==0){
                padre.matriz[i][pos]=2;
                return count;
            }count++;
        }
        return -1;
    }
    public void initResources() {
        // create the game playfield
        playfield = new PlayField();
        arrowgroup = playfield.addGroup(new SpriteGroup("arrow"));
        boardgroup = playfield.addGroup(new SpriteGroup("board"));
        chipgroup = playfield.addGroup(new SpriteGroup("chip"));
        winLoseGroup = playfield.addGroup(new SpriteGroup("msg"));
        // associate the playfield with a background
        background = new ImageBackground(getImage("resources/background.jpg"), 640, 480);
        playfield.setBackground(background);
        BufferedImage boardimage = getImage("resources/Tablero.png");
        board = new Sprite(boardimage, 70, 70);
        boardgroup.add(board);
        BufferedImage arrowimage = getImage("resources/arrow.png");
        arrow = new Sprite(arrowimage, 74, 20);
        arrowgroup.add(arrow);
    }
    public void render(Graphics2D g) {
        playfield.render(g);
    }

    public void update(long elapsedTime) {
        playfield.update(elapsedTime);
        if (keyPressed(KeyEvent.VK_RIGHT)&& pos<6) {
            arrow.moveX(73);
            pos++;
        }
        if (keyPressed(KeyEvent.VK_LEFT)&& pos>0) {
            arrow.moveX(-73);
            pos--;
        }
        if (keyPressed(KeyEvent.VK_SPACE)&&activeflag==0) {
            activeflag=1;
            dropflag=1;
            if(turn==0){
                chip = new Sprite(getImage("resources/WhiteChip.png"));
                turn =1;
            }else{
                chip = new Sprite(getImage("resources/BlackChip.png"));
                turn =0;
            }
            
            //chip.setLocation(arrow.getX(), arrow.getY());
            chip.setLocation(arrow.getX(), 80);//intervalos de 68
            chip.setVerticalSpeed(0.2);
            level = insertInMatrix();
            playerMove();
            if(level>=0){
                boardgroup.add(chip);
            }else{
                 activeflag=0;
                 dropflag=0;
            }
            boardgroup.add(board);
            if((padre.valuacion())<=-10000){
                System.out.println("GANA NEGRAS");
                BufferedImage msgimage = getImage("resources/Ganaste.png");
                msg = new Sprite(msgimage, 150, 150);
                winLoseGroup.add(msg);
                turn=1;
            }else if(boardFull()){
                System.out.println("Fin del juego, empate");
                BufferedImage msgimage = getImage("resources/Empate.png");
                msg = new Sprite(msgimage, 150, 150);
                winLoseGroup.add(msg);
            }
            //System.out.println(level);

        }
        if(dropflag==1 && activeflag==1 && chip.getY()>=420-68.5*level){
            chip.setVerticalSpeed(0);
            activeflag=0;
            dropflag=0;
        }
        if(turn==0&&activeflag==0){
            activeflag=1;
            int[][] old = padre.getMatriz();
            Arbol test=new Arbol(padre);
            test.genArbol();
            padre=test.poda();
            padre.tipo=true;
            System.out.println(padre);
            int place = getMove(old, padre.getMatriz());
            System.out.println(place);
            while(place>pos){
                arrow.moveX(73);
                pos++;
            }
            while(place<pos){
                arrow.moveX(-73);
                pos--;
            }
            chip = new Sprite(getImage("resources/WhiteChip.png"));
            turn =1;
            chip.setLocation(arrow.getX(), 80);//intervalos de 68
            chip.setVerticalSpeed(0.2);
            dropflag=1;
            level = insertInMatrix();
            if(level>=0){
                boardgroup.add(chip);
            }else{
                 activeflag=0;
                 dropflag=0;
            }
            boardgroup.add(board);
            if(padre.valuacion()>=10000){
                System.out.println("FIN DEL JUEGO");
                BufferedImage msgimage = getImage("resources/Perdiste.png");
                msg = new Sprite(msgimage, 150, 150);
                winLoseGroup.add(msg);
                activeflag=1;
            }else if(boardFull()){
                System.out.println("Fin del juego, empate");
                BufferedImage msgimage = getImage("resources/Empate.png");
                msg = new Sprite(msgimage, 150, 150);
                winLoseGroup.add(msg);
            }
        }
    }
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Connect4(), new Dimension(640,480), false);
        game.start();
    }
}