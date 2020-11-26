package com.example.module_2_week_2_recicler_view.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MockGenerator {
    public static List<Mock> Generate(int count){

        List<Mock> mocks = new ArrayList<Mock>(count);

        Random random = new Random();

        for (int i=0;i<count;i++){
            mocks.add(new Mock(UUID.randomUUID().toString(),random.nextInt(200)));
        }
        return mocks;
    }
}
