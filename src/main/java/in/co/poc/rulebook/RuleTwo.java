package in.co.poc.rulebook;

import java.util.Map;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "ruleTwo", description = "rule two")
public class RuleTwo {

  private int priority;

  @Condition
  public boolean when(@Fact("isRuleTwo") boolean isRuleTwo, @Fact("response") Map response) {
    response.put("ruleTwoResponse", isRuleTwo ? "success" : "failure");
    if (!isRuleTwo) {
      throw new RuntimeException();
    }
    return isRuleTwo;
  }

  @Action
  public void then() throws Exception {
    System.out.println("rule two success");
  }

  @Priority
  public int fetchPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }
}
