package test.ex4_java_client;

import ex4_java_client.AgentV1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentV1Test {

    @Test
    void isAvailable() {
        AgentV1 a=new AgentV1(1,0, -1, 1d, 1d, 1d, 1d, 1500);
        AgentV1 b=new AgentV1(0,2, 1, 1d, 1d, 1d, 1d, 1500);
        Assertions.assertTrue(a.isAvailable());
        Assertions.assertFalse(b.isAvailable());
    }
}