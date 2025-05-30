package io.confluent.ksqldb.udaf;

import io.confluent.ksql.function.udaf.Udaf;
import io.confluent.ksql.function.udaf.UdafDescription;
import io.confluent.ksql.function.udaf.UdafFactory;

@UdafDescription(
    name="BoyerMooreMajorityVote",
    description = "UDAF that implements the Boyer-Moore majority vote algorithm"
    )
public final class BoyerMooreMajorityVoteUdaf {
    
    private BoyerMooreMajorityVoteUdaf() {
        // Prevent instantiation
    }

    @UdafFactory(description = "Find the majority element using Boyer-Moore algorithm")
    public static Udaf<String, Integer, String> createUdaf() {
        return new BoyerMooreMajorityVoteUdafImpl();
    }

    private static class BoyerMooreMajorityVoteUdafImpl implements Udaf<String, Integer, String> {

        private static String CANDIDATE;

        @Override
        public Integer initialize() {
            CANDIDATE = null;
            return 0;
        }

        @Override
        public Integer aggregate(String value, Integer aggregate) {
            if (aggregate == 0) {
                CANDIDATE = value;
                return 1;
            }
            if (CANDIDATE != null && CANDIDATE.equals(value)) {
                return aggregate + 1;
            } else {
                return aggregate - 1;
            }
        }

        @Override
        public Integer merge(Integer previousCount, Integer currentCount) {
            // not really merging counts, but rather determining if the candidate is still valid
            return previousCount + currentCount;
        }

        @Override
        public String map(Integer aggregate) {
            return CANDIDATE;
        }
    }
}
