Booking[] bl = new Booking[] {
 new Booking(new NormalCab("X", 0), new Request(1, 3, 1000)),
 new Booking(new NormalCab("Y", 5), new Request(1, 3, 1000)),
 new Booking(new NormalCab("Z", 1000), new Request(1, 3, 1000)),
 new Booking(new NormalCab("X", 0), new Request(50, 3, 1000)),
 new Booking(new NormalCab("Y", 5), new Request(50, 3, 1000)),
 new Booking(new NormalCab("Z", 1000), new Request(50, 3, 1000)),
 new Booking(new NormalCab("X", 0), new Request(9, 3, 1000)),
 new Booking(new NormalCab("Y", 5), new Request(9, 3, 1000)),
 new Booking(new NormalCab("Z", 1000), new Request(9, 3, 1000))
}

Arrays.sort(bl);

for (Booking b : bl) { 
    System.out.println(b);
}
