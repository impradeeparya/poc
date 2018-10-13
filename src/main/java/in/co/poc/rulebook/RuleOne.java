package in.co.poc.rulebook;

import java.util.Map;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "RuleOne", description = "rule one")
public class RuleOne {

  private int priority;

  @Condition
  public boolean when(@Fact(value = "isRuleOne") boolean isRuleOne,
      @Fact("response") Map response) {
    response.put("ruleOneResponse", isRuleOne ? "success" : "failure");
    return isRuleOne;
  }

  @Action
  public void then() throws Exception {
    System.out.println("rule one success");
  }

  @Priority
  public int fetchPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }
}
