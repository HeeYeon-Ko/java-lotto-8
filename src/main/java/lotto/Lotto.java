package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> copy = new ArrayList<>(numbers);
        Collections.sort(copy);
        this.numbers = Collections.unmodifiableList(copy); // 정렬+불변
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        boolean[] seen = new boolean[46]; // 1~45
        for (int n : numbers) {
            if (n < 1 || n > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            if (seen[n]) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
            }
            seen[n] = true;
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString(); // [8, 21, 23, ...] 형태 출력
    }

    // 로또 발행 함수
    public static List<Lotto> createLottos(int count){
        System.out.println("\n" + count + "개를 구매했습니다.");
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> nums = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto ticket = new Lotto(nums); // 검증 + 정렬
            tickets.add(ticket);
            System.out.println(ticket);
        }
        return tickets;
    }
}
