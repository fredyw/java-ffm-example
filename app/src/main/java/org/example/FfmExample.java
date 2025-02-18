package org.example;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.nio.file.Path;

public class FfmExample {
    private static final Linker LINKER = Linker.nativeLinker();
    private static final Path LIB_PATH = Path.of("../native/target/release/libnative.so");

    public record Input(int a, int b, char op) {
    }

    public static int calculate(Input input) throws Throwable {
        try (Arena arena = Arena.ofConfined()) {
            StructLayout inputLayout = MemoryLayout.structLayout(
                    ValueLayout.JAVA_INT.withName("a"),
                    ValueLayout.JAVA_INT.withName("b"),
                    ValueLayout.JAVA_BYTE.withName("op"),
                    MemoryLayout.paddingLayout(3))
                .withName("Input");

            MemorySegment inputSegment = arena.allocate(inputLayout);
            inputLayout.varHandle(MemoryLayout.PathElement.groupElement("a"))
                .set(inputSegment, 0, input.a);
            inputLayout.varHandle(MemoryLayout.PathElement.groupElement("b"))
                .set(inputSegment, 0, input.b);
            inputLayout.varHandle(MemoryLayout.PathElement.groupElement("op"))
                .set(inputSegment, 0, (byte) input.op);

            SymbolLookup nativeSymbol = SymbolLookup.libraryLookup(LIB_PATH, arena);
            MemorySegment calculateSegment = nativeSymbol.findOrThrow("calculate");
            FunctionDescriptor calculateFuncDesc = FunctionDescriptor.of(ValueLayout.JAVA_INT, inputLayout);
            MethodHandle executeHandle = LINKER.downcallHandle(calculateSegment, calculateFuncDesc);
            return (int) executeHandle.invoke(inputSegment);
        }
    }

    public static void main(String[] args) throws Throwable {
        System.out.println(calculate(new Input(1, 2, '+')));
        System.out.println(calculate(new Input(4, 2, '-')));
        System.out.println(calculate(new Input(1, 2, '*')));
        System.out.println(calculate(new Input(4, 2, '/')));
    }
}
