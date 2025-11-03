package lotto;

import java.util.List;

public class LottoResult {

    // 등수 판정
    static Rank rankOf(int match, boolean bonusMatched) {
        if (match == 6) return Rank.FIRST;
        if (match == 5 && bonusMatched) return Rank.SECOND;
        if (match == 5) return Rank.THIRD;
        if (match == 4) return Rank.FOURTH;
        if (match == 3) return Rank.FIFTH;
        return null; // 낙첨
    }

    // 일치 개수 세기 (Lotto 사용)
    static int countMatch(Lotto ticket, List<Integer> win) {
        int cnt = 0;
        for (int n : ticket.getNumbers()) {
            if (win.contains(n)) cnt++;
        }
        return cnt;
    }

    // 통계 출력(집계 + 수익률)
    static void printResult(List<Lotto> tickets, List<Integer> win, int bonus, int paid) {
        int[] counts = new int[Rank.values().length];
        long total = 0L;

        for (Lotto t : tickets) {
            int match = countMatch(t, win);
            boolean bonusHit = t.getNumbers().contains(bonus);
            Rank r = rankOf(match, bonusHit);
            if (r == null) continue;
            counts[r.ordinal()]++;
        }

        for (Rank r : Rank.values()) {
            total += (long) r.prize() * counts[r.ordinal()];
        }

        printRankStats(counts);
        printYieldRate(total, paid);
    }

    // 등수별 통계 출력
    private static void printRankStats(int[] counts) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.printf("3개 일치 (%,d원) - %d개%n", Rank.FIFTH.prize(),  counts[Rank.FIFTH.ordinal()]);
        System.out.printf("4개 일치 (%,d원) - %d개%n", Rank.FOURTH.prize(), counts[Rank.FOURTH.ordinal()]);
        System.out.printf("5개 일치 (%,d원) - %d개%n", Rank.THIRD.prize(),  counts[Rank.THIRD.ordinal()]);
        System.out.printf("5개 일치, 보너스 볼 일치 (%,d원) - %d개%n", Rank.SECOND.prize(), counts[Rank.SECOND.ordinal()]);
        System.out.printf("6개 일치 (%,d원) - %d개%n", Rank.FIRST.prize(), counts[Rank.FIRST.ordinal()]);
    }

    // 수익률 출력
    private static void printYieldRate(long total, int paid) {
        double rate = Math.round((total * 100.0 / paid) * 10) / 10.0;
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }
}
