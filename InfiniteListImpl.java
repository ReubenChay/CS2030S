import java.util.function.BiFunction;
import java.util.ArrayList; 
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Optional;

class InfiniteListImpl<T> implements InfiniteList<T> {

    private final Lazy<T> head; 
    private final Supplier<InfiniteList<T>> tail; 
    private static final int ONE = 1; 

    protected InfiniteListImpl(Lazy<T> head, Supplier<InfiniteList<T>> tail) {
        this.head = head; 
        this.tail = tail; 
    }

    static <T> InfiniteList<T> generate(Supplier<? extends T> s) {
        Lazy<T> head = Lazy.of(s);
        Supplier<InfiniteList<T>> tail = () -> 
            InfiniteListImpl.generate(s);
        return new InfiniteListImpl<T>(head, tail);
    }

    static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        Lazy<T> head = Lazy.ofNullable(seed); 
        Supplier<InfiniteList<T>> tail = () -> 
            InfiniteList.iterate(next.apply(seed), next); 
        return new InfiniteListImpl<T>(head, tail); 
    }

    // prints 1st element of infinite list then returns rest of list as InfiniteList 
    @Override 
    public InfiniteList<T> peek() {
        head.get().ifPresent(x -> System.out.println(x));
        return this.tail.get(); 
    }

    @Override 
    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper) {
        Supplier<R> headSupplier = () -> { 
            Lazy<R> h = this.head.map(mapper); 
            return h.getActualValue(); 
        };
        Lazy<R> newHead = Lazy.of(headSupplier);  
        Supplier<InfiniteList<R>> newTail = () -> {
            return tail.get().map(mapper);
        };
        return new InfiniteListImpl<R>(newHead, newTail); 
    }

    @Override 
    public InfiniteList<T> filter(Predicate<? super T> predicate) {
        Lazy<T> newHead = this.head.filter(predicate);  
        Supplier<InfiniteList<T>> newTail = () -> {
            return tail.get().filter(predicate);
        };
        return new InfiniteListImpl<T>(newHead, newTail); 
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        this.head.get().ifPresent(action); 
        this.tail.get().forEach(action);
    }

    @Override 
    public InfiniteList<T> limit(long n) {
        if (n < 1) { 
            return new EmptyList<T>(); 
        } else if (this.isEmpty()) {
            return this;
        } else if (n == 1) {
            Supplier<InfiniteList<T>> newTail = () -> {
                if (this.head.get().equals(Optional.<T>empty())) { 
                    return tail.get().limit(n);
                } else { 
                    return new EmptyList<T>(); 
                }
            };
            return new InfiniteListImpl<T>(this.head, newTail); 
        } else { 
            Supplier<InfiniteList<T>> newTail = () -> {
                if (this.head.get().equals(Optional.<T>empty())) {
                    return tail.get().limit(n); 
                } else { 
                    return tail.get().limit(n - 1);
                }
            }; 
            return new InfiniteListImpl<T>(this.head, newTail); 
        }
    }

    @Override 
    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        if (this.isEmpty()) {
            return this; 
        } else {    
            Lazy<T> newHead = this.head.filter(predicate); 
            Supplier<InfiniteList<T>> newTail = () -> {
                /*
                   Lazy<Boolean> headEmpty = Lazy.<Boolean>of(() ->
                   this.head.get().equals(Optional.<T>empty()));
                   */
                Lazy<Boolean> newHeadEmpty = Lazy.<Boolean>of(() -> 
                        newHead.get().equals(Optional.<T>empty()));

                Lazy<Boolean> thisHeadEmpty = Lazy.<Boolean>of(() -> 
                        this.head.get().equals(Optional.<T>empty()));
                /*
                   if (headEmpty.getActualValue() == false && 
                   newHeadEmpty.getActualValue() == true) { 
                   */

                if (newHeadEmpty.getActualValue() == true && 
                        thisHeadEmpty.getActualValue() == false) { 
                    return new EmptyList<T>(); 
                } else {
                    return this.tail.get().takeWhile(predicate); 
                }
            };
            return new InfiniteListImpl<T>(newHead, newTail);
            }
        }

        @Override 
        public Object[] toArray() {
            ArrayList<T> list = new ArrayList<T>(); 
            Consumer<? super T> adder = x -> list.add(x); 
            this.forEach(adder); 

            return list.toArray();
        }

        @Override 
        public long count() { 
            if (!this.head.get().equals(Optional.<T>empty())) { 
                return ONE + tail.get().count(); 
            } else {
                return tail.get().count(); 
            }
            /*
               long count = 0; 
               Consumer<? super T> adder = x -> count += 1; 
               this.forEach(adder);
               return count; 
               */
        }

        @Override 
        public <U> U reduce (U identity, BiFunction<U, ? super T, U> accumulator) { 
            U nextIdentity = this.head.get().map(x -> accumulator.apply(identity, x))
                .orElseGet(() -> identity); 
            return this.tail.get().reduce(
                    nextIdentity, accumulator);

            /*
               Optional<T> headOp = this.head.get();
               if (headOp.equals(Optional.<T>empty())) { 
               return this.tail.get().reduce(identity, accumulator); 
               } else { 
               return this.tail.get().reduce(
               accumulator.apply(identity, headOp.get()), accumulator);
               }
               */
        }


        @Override 
        public boolean isEmpty() {
            return false; 
        }

    }


