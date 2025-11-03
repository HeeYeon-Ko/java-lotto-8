package lotto;

import static lotto.WinningNumbers.inputWinningNumbersWithBonus;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int money = readAmountUntilValid();
        int count = money / 1000;

        List<Lotto> tickets = Lotto.createLottos(count);
        WinningNumbers win = inputWinningNumbersWithBonus();
        LottoResult.printResult(tickets, win.getNumbers(), win.getBonus(), money);

    }

    private static int readAmountUntilValid() {
        while (true) {
            try {
                return inputAmount();   // ← 한 번 시도
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // [ERROR] 출력
            }
        }
    }

    // 구입금액 입력 함수
    private static int inputAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String line = Console.readLine().trim();

        int money;
        try {
            money = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해 주세요.");
        }

        if (money < 1000 || money % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1,000원 단위로 입력해 주세요.");
        }
        return money;
    }

}
