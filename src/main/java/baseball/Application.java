package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private final static int BALL = 0;
    private final static int STRIKE = 1;
    private final static int COMPUTER_NUM_SIZE = 3;
    private static List<Integer> computerNum = new ArrayList<>();

    public static void main(String[] args) {
        gameStart();
    }

    private static void gameStart() {
        makeComputerNum();
        playingGame();

        if (checkRestartGame())
            gameStart();
    }

    /**
     * 컴퓨터의 3자리 숫자를 만드는 메소드
     */
    private static void makeComputerNum() {
        computerNum.clear();
        while (computerNum.size() < COMPUTER_NUM_SIZE) {
            int num = Randoms.pickNumberInRange(1, 9);
            if (!computerNum.contains(num)) {
                computerNum.add(num);
            }
        }
    }

    /**
     * 게임을 실제로 플레이하는 구간 숫자를 계속 추측함.
     */
    private static void playingGame() {
        String gameString = "";
        System.out.println("숫자 야구 게임을 시작합니다.");
        while (!gameString.equals("3스트라이크")) {
            System.out.println("숫자를 입력해주세요 : ");
            String userInput = Console.readLine();
            // 콜 카운트를 checkBallCount 메소드를 통하여 산출해 낸 다음 PrintBallCount를 통하여 적절한 String 으로 변환한다.
            gameString = printBallCount(checkBallCount(userInput));
            System.out.println(gameString);
        }
    }

    /**
     * ballCount와 StrikeCount를 체크해서 List형태로 반환해준다.
     */
    private static List<Integer> checkBallCount(String userInput) {
        List<Integer> listBS = new ArrayList<>();
        int totalCount = 0;
        int strikeCount = 0;

        for (int i = 0; i < COMPUTER_NUM_SIZE; i++) {
            if (userInput.contains(computerNum.get(i).toString())) {
                totalCount++;
            }
            if (Character.getNumericValue(userInput.charAt(i)) == computerNum.get(i)) {
                strikeCount++;
            }
        }
        listBS.add(totalCount - strikeCount);
        listBS.add(strikeCount);
        return listBS;
    }

    /**
     * BALL Count 와 STRIKE Count를 문자열로 변환하여 출력하여줌
     */
    private static String printBallCount(List<Integer> listBS) {
        String returnString = "";
        if (listBS.get(BALL) != 0)
            returnString = listBS.get(BALL) + "볼 ";
        if (listBS.get(STRIKE) != 0)
            returnString += listBS.get(STRIKE) + "스트라이크";
        if (listBS.get(BALL) == 0 && listBS.get(STRIKE) == 0)
            returnString = "낫씽";
        return returnString;
    }

    /**
     * 게임이 끝난 후 사용자에게 다시 시작할 것인지 물어보는 메소드
     */
    public static boolean checkRestartGame() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

        String gameChoiceNum = Console.readLine();

        if (gameChoiceNum.equals("1"))
            return true;
        else if (gameChoiceNum.equals("2"))
            return false;
        //else를 사용하지않고 잘못 입력시 예외처리 하기
        throw new IllegalArgumentException("1과 2중에 입력하셔야 합니다. ");
    }

}