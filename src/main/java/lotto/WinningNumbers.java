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
        if (nums.size() != 6) throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        if (nums.stream().distinct().count() != 6) throw new IllegalArgumentException("[ERROR] 로또 번호는 중복 금지여야 합니다.");
        if (nums.stream().anyMatch(n -> n < 1 || n > 45)) throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        if (bonus < 1 || bonus > 45) throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        if (nums.contains(bonus)) throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨번호와 중복 금지여야 합니다.");
    }

    private static WinningNumbers readWinningUntilValid() {
        while (true) {
            try {
                return inputWinningNumbersWithBonus();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // [ERROR] 출력 후 재입력
            }
        }
    }

    // 당첨 번호 + 보너스 번호 입력 함수
    public static WinningNumbers inputWinningNumbersWithBonus(){
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String[] tokens = Console.readLine().split(",");
        List<Integer> nums = new java.util.ArrayList<>(tokens.length);
        for (String t : tokens) {
            String s = t.trim();
            try { nums.add(Integer.parseInt(s)); }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 숫자만 입력해 주세요.");
            }
        }

        System.out.println("\n보너스 번호를 입력해 주세요.");
        String b = Console.readLine().trim();
        int bonus;
        try { bonus = Integer.parseInt(b); }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해 주세요.");
        }

        return new WinningNumbers(nums, bonus);
    }

    public List<Integer> getNumbers() { return winningNumbers; }

    public int getBonus() { return bonusNumber; }
}
