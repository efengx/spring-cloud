package com.yangst.cloud.demo.common.shardingrule;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 分库策略
 * 主要看doEqualSharding方法（譬如select * from t_order where user_id = 11就是equal），
 * availableTargetNames就是所有的库名（ds_0,ds_1),shardingValue就是在DataSourceConfig里指定的user_id,
 * 代码就是如果user_id是偶数就算到ds_0数据库，其他的就放ds_1数据库。而另外的两个方法，
 * doIn和doBetween是用在如where user_id in (1,23,7)和where user_id between(1, 6)。 
 * table的策略和db的策略是一样的，算法可以自己定。 
 * 上面两个都是实现的SingleKeyShardingAlgorithm，也就是单列策略，也可以使用多列策略，譬如user_id 和 order_id同时符合某个条件的，分到哪个表。 
 * new TableShardingStrategy(Arrays.asList(“order_id”, “order_type”, “order_date”), new MultiKeyShardingAlgorithm()))
 * @author YST
 *
 */
public class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % availableTargetNames.size() + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Long value : shardingValue.getValues()) {
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(value % availableTargetNames.size() + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
                                                ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % availableTargetNames.size() + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }

}
