package in.co.poc.rulebook;

import java.util.Map;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "ruleThree", description = "rule three")
public class RuleThree {

  private int priority;

  @Condition
  public boolean when(@Fact("isRuleThree") boolean isRuleThree, @Fact("response") Map response) {
    response.put("ruleThreeResponse", isRuleThree ? "success" : "failure");
    if (!isRuleThree) {
      throw new RuntimeException();
    }
    return isRuleThree;
  }

  @Action
  public void then() throws Exception {
    System.out.println("rule three success");
  }

  @Priority
  public int fetchPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }
}
