package lotto;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int money = inputAmount();
        int count = money / 1000;

        Lotto.createLottos(count);


    }

    //구입금액 입력 함수
    private static int inputAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int money = Integer.parseInt(Console.readLine());

        if (money < 1000 || money % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1,000원 단위로 입력해주세요.");
        }
        return money;
    }

    //당첨 번호 + 보너스 번호 입력 함수
    //private static List<Integer> inputWinningNumbersWithBonus(){}
}
