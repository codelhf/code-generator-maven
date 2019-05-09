package ${DaoPackageName}.base;

import tk.mybatis.mapper.common.BaseSelectMapper;
import tk.mybatis.mapper.common.SelectByExampleMapper;
import tk.mybatis.mapper.common.SelectCountByExampleMapper;
import tk.mybatis.mapper.common.SelectOneByExampleMapper;

public interface ViewMapper<T> extends BaseSelectMapper<T>, SelectByExampleMapper<T>, SelectCountByExampleMapper<T>, SelectOneByExampleMapper<T> {

}