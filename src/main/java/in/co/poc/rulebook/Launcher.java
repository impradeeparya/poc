package in.co.poc.rulebook;

import java.util.HashMap;
import java.util.Map;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

public class Launcher {
  public static void main(String... args) {

    Map<String, Object> map = new HashMap<>();

    try {
      // create facts
      Facts facts = new Facts();
      facts.put("isRuleOne", true);
      facts.put("response", map);
      facts.put("isRuleTwo", true);
      facts.put("isRuleThree", true);

      RuleOne ruleOne = new RuleOne();
      ruleOne.setPriority(1);
      RuleTwo ruleTwo = new RuleTwo();
      ruleTwo.setPriority(3);
      RuleThree ruleThree = new RuleThree();
      ruleThree.setPriority(2);

      // CompositeRule compositeRule = new CompositeRule();
      // compositeRule.addRule(ruleOne);
      // compositeRule.addRule(ruleTwo);
      // compositeRule.addRule(ruleThree);

      // create rules
      Rules rules = new Rules();
      // rules.register(compositeRule);
      rules.register(ruleOne);
      rules.register(ruleThree);
      rules.register(ruleTwo);

      // create a rules engine and fire rules on known facts
      RulesEngine rulesEngine = new DefaultRulesEngine();
      // RulesEngine rulesEngine =
      // RulesEngineBuilder.aNewRulesEngine().withSkipOnFirstFailedRule(true).build();
      rulesEngine.fire(rules, facts);
    } catch (Exception e) {
      System.out.println("exception occured");
      System.out.println(map);
    } finally {
      System.out.println(map);
    }
  }
}

