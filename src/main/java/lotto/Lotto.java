package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
    //로또 발행 함수
    public static void createLottos(int count){
        System.out.println("\n" + count + "개를 구매했습니다.");
        for (int i = 0; i < count; i++) {
            List<Integer> lottos = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            printLottos(lottos);
        }
    }

    //로또 출력 함수
    public static void printLottos(List<Integer> nums){
        Collections.sort(nums);
        System.out.println(nums);
    }
}
