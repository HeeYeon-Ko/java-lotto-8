package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class WinningNumbers {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = List.copyOf(winningNumbers.stream().sorted().toList());
        this.bonusNumber = bonusNumber;
    }

    public static void validate(List<Integer> nums, int bonus) {
        if (nums.size() != 6) throw new IllegalArgumentException("[ERROR] 6개 입력");
        if (nums.stream().distinct().count() != 6) throw new IllegalArgumentException("[ERROR] 중복 금지");
        if (nums.stream().anyMatch(n -> n < 1 || n > 45)) throw new IllegalArgumentException("[ERROR] 1~45 범위");
        if (bonus < 1 || bonus > 45) throw new IllegalArgumentException("[ERROR] 보너스 1~45");
        if (nums.contains(bonus)) throw new IllegalArgumentException("[ERROR] 보너스는 당첨번호와 중복 금지");
    }


    //당첨 번호 + 보너스 번호 입력 함수
    public static WinningNumbers inputWinningNumbersWithBonus(){
        System.out.println("\n당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = Arrays.stream(Console.readLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        System.out.println("\n보너스 번호를 입력해 주세요.");
        int bonusNumber = Integer.parseInt(Console.readLine());

        return new WinningNumbers(winningNumbers, bonusNumber);
    }
}
