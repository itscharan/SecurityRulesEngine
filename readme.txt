Security Rules Engine

Which accepts or rejects security data based on a set of rules.

Core features:
1) RuleStore
2) Security data objects
3) RulesEngine


Rule semantics:
A rule is formatted in the below format with | as delimiter 
Param1|Param2|Param3|Param4|Param5|Param6

Accept bond where a coupon is greater than 10.5

eg: bond|coupon|GTHAN|10.5|NUMERIC|ACCEPT


Parameter -> Value
Param1    -> financial instrument
Param2    -> attribute of the financial instrument
Param3    -> is a relational operator (EQUAL, NEQUAL, GTHAN, LTHAN, GETHAN, LETHAN)
Param4    -> value of the atrribute
Param5    -> NUMERIC or TEXT or DATE
Param6	  -> ACCEPT or REJECT


eg: 
param1 -> bond
param2 -> coupon or issuer or nextcoupondate or settlementdate or mature date or face value
param3 -> EQUAL or NEQUAL or GTHAN or LTHAN or GETHAN or LETHAN
param4 -> 100.20 or MUNICIPAL or TODAY+0 or TODAY+360 or TODAY-100
param5 -> NUMERIC for numerical values
	  TEXT for issuer name or other text related fields
	  DATE for date related fields

param6 -> ACCEPT or REJECT


Note: When a business rule has reject, try to convert it to ACCEPT rule.

eg1: reject equity that has dividend less than 100.0
Reject rule: equity|dividend|LTHAN|100.0|NUMERIC|REJECT

this is re writtern as an ACCEPT rule which is below
Accept rule: equity|dividend|GETHAN|100.0|NUMERIC|ACCEPT


eg2: reject a security other than issuer "MUNICIPAL"
Reject rule: bond|issuer|NEQUAL|MUNICIPAL|TEXT|REJECT
Accept rule: bond|issuer|EQUAL|MUNICIPAL|TEXT|ACCEPT


Param5 has a date which is in the below format

TODAY+(number of days) or TODAY-(number of days)



=============================================================
About:
RuleStore api stores all the valid rules, has the ability to parse and validate a rule.
Security data object stores information regarding a security which has a hashMap to store all attributes and values
RulesEngine executes all rules on a given data object to either accept or reject a security.

Assumptions:
All numericals are in single currency.
Given rules are only accepted rules (See example to convert it to accepted rules)

Execution Steps:
Create a security data instance either bond or equity.
add parameter and its value 

Create a RuleStore instance, add rule with rule name

Create a Rule Engine instance and pass rulestore and security data instances
Finally run engine to get output(either accepted or rejected)

Required libraries:
1) JUnit4
2) Apache Joda Time (http://www.joda.org/joda-time/)

=============================================================


Future work:
1)RulesStore can be extended to recieve rules from multiple streams like flat files, xml and through http post etc

2)To implement a logic to compare numerical values and to decide to accept or reject a security

3)Can extend Rules engine to other financial instruments

4) Add logger to generate log files with appropriate information

5) Multi threading way to achive better performance and can scale

6) Add real time events in logger to get push notifications when a security is rejected based on rules