package com.example.lesson2.lesson4;

import android.util.Log;

public class LambdaJava {
    {
        int a;
    }
    public static void main() {
        Operation operation = new Operation() {
            @Override
            public int calculate(int x, int y) {
                return x + y;
            }
        };
        int z = operation.calculate(2, 3);
        Log.d("MyLogs", "z = " + z);
        Operation operation2 = (x, y) -> x + y;
        int z2 = operation2.calculate(2, 3);
        Log.d("MyLogs", "z2 = " + z2);


    }
}

interface Operation {
    int calculate(int x, int y);
}