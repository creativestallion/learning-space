package pairprogramming;

import pairprogramming.model.Crate;
import pairprogramming.model.Delivery;

import java.util.*;

public final class Solution {

    private final int bufferTime = 5;

    /**
     * Takes a set of deliveries and split them into batches such that each batch, when packed into
     * crates, does not exceed the specified maximum number of crates a vehicle can hold.
     *
     * @param deliveries The set of deliveries to batch.
     * @param max        The maximum number of crates per batch; essentially the number of crates that a vehicle can hold.
     * @return A set of delivery batches.
     */
    public Set<Set<Delivery>> problem1(final Set<Delivery> deliveries, final int max) {
        final Set<Set<Delivery>> ans = new HashSet<>();
        final Set<Delivery> temp = new HashSet<>();
        for (Delivery del : deliveries) {
            temp.add(del);
            Set<Crate> crates = pack(temp);
            if (crates != null && crates.size() == max) {
                ans.add(new HashSet<>(temp));
                temp.clear();
            }
        }
        ans.add(new HashSet<>(temp)); // last ones
        return ans;
    }

    public Set<Set<Delivery>> batch(Set<Delivery> deliveries, int max) {
        // TODO (for candidate): Finish this implementation.
        Set<Set<Delivery>> vehicles = new HashSet<>();
        Set<Delivery> vehicle = new HashSet<>();
        if (deliveries == null || deliveries.size() == 0) {
            return new HashSet<>();
        }
        for (Delivery delivery : deliveries) {
            vehicle.add(delivery);
            int crateSize = pack(vehicle).size();
            if (crateSize > max) {
                vehicle.remove(delivery);
                vehicles.add(vehicle);
                vehicle = new HashSet<>();
                vehicle.add(delivery);
            }
        }

        vehicles.add(vehicle);

        return vehicles;
    }


    /**
     * Receives a set of deliveries, and packs the deliveries' contents into crates, according to some
     * restrictions on article weight and volume.
     *
     * @param deliveries The set of deliveries to consider.
     * @return A set of crates which contain the deliveries' contents.
     */
    public Set<Crate> pack(final Set<Delivery> deliveries) {
        return null;
    }

    /*
    q2
    Every delivery has a promised time window. Two windows can be served by a single dispatch if they overlap or fall within
    an allowed consolidation buffer of each other. Windows that are only connected through a chain of other windows must
    still travel together.


    canShare(a, a) = true
    canShare(a, b) = canShare(b, a)                              // symmetric
    canShare(a, b) AND canShare(b, c)  does NOT imply  canShare(a, c)

    Windows:  A [09:00-10:00]   B [09:45-10:45]   C [10:30-11:30]
    D [13:00-14:00]   E [13:10-13:40]
    buffer = 15 min

    canShare(A, B) ──► true      (overlap)
    canShare(B, C) ──► true      (overlap)
    canShare(A, C) ──► false     (gap 30 min > buffer)
    canShare(C, D) ──► false     (gap 90 min)
    canShare(D, E) ──► true      (E inside D)

    A ── B ── C   are one dispatch even though A and C alone cannot share.

    Given [A, B, C, D, E]

  { A, B, C }      ──►  connected chain A-B-C          (Valid)
  { D, E }         ──►  connected D-E                  (Valid)
  ─────────────────────────────────────────────────────────────
  { A, B }         ──►  splits C off its chain         (Invalid)
  { C }            ──►  C belongs with A, B            (Invalid)
  { D, E }         ──►                                 (Valid)
  ─────────────────────────────────────────────────────────────
  { C, D }         ──►  C and D cannot share, no chain (Invalid)
     */

    /**
     * Groups deliveries into dispatch batches: two deliveries belong to the same batch exactly when
     * they are connected through a chain of shareable windows. No two deliveries in different batches
     * may share a dispatch.
     *
     * @param deliveries The deliveries to group.
     * @return Dispatch batches (connected groups of deliveries).
     */
    public Set<Set<Delivery>> consolidate(final Set<Delivery> deliveries) {
        // assumptions -> deliveries are sorted based on start time, if not i'll sort.
        final Set<Set<Delivery>> ans = new HashSet<>();
        final Set<Delivery> temp = new HashSet<>();

        final List<Delivery> list = deliveries.stream()
                .filter(e -> e.id()!=null)
                .sorted((a,b) -> a.startTime()-b.startTime())
                .toList();

        temp.add(list.getFirst());
        Delivery lastDel = list.getFirst();
        // A = [0,5], B = [1,2], C = [4,6], buffer = 0
        for (int i = 1; i < list.size(); i++) {
            lastDel = list.get(i-1).endTime() > lastDel.endTime() ? list.get(i-1) : lastDel;
            final boolean flag = canShare(lastDel, list.get(i));
            if (!flag) {
                ans.add(new HashSet<>(temp));
                temp.clear();
            }
            temp.add(list.get(i));
        }
        if (!temp.isEmpty()) ans.add(temp);
        return ans;
        // TC: o(list.size()) * o(temp.clear())
    }

    /**
     * Returns whether two deliveries' windows can be served by a single dispatch, i.e. they overlap
     * or lie within the allowed consolidation buffer of one another.
     *
     * @param a The first delivery.
     * @param b The second delivery.
     * @return true if the two windows can share one dispatch.
     */
    // A[540,600], B[585,645], buffer=15.
    public boolean canShare(final Delivery a, final Delivery b) {
        var endA = a.endTime();
        var startB = b.startTime();
        return startB <= endA + bufferTime;
    }
}
