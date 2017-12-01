package ${packageName};

import com.zzq.common.ibatis.annotation.IBatisMysqlMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import java.util.List;
${importStr}

/**
 *
 * ${description}数据访问接口
 *
 * @version
 * <pre>
 * Created by ${author} on ${createDate}.
 * </pre>
 * @since 1.0
 */
@IBatisMysqlMapper
public interface ${className} {
	
	/**
     * 插入${description}
     * @param ${entityName}
     * @return
     * @date ${createDate}
     */
    int insert(${entityClassName} ${entityName});
	
	/**
     * 更新${description}记录
     * @param ${entityName}
     * @return
     * @date ${createDate}
     */
    int updateByKey(${entityClassName} ${entityName});
	
	/**
     * 根据id删除${description}记录
     * @param id
     * @date ${createDate}
     */
    void deleteByKey(@Param(value = "id") String id);
	
	/**
     * 根据ID获取${description}记录
     * @param id
     * @return
     * @date ${createDate}
     */
    SystemParam selectByKey(@Param(value = "id") String id);
	
    /**
     * 模糊匹配，分页查询${description}记录列表
     * @param ${entityName}
     * @param rowBounds
     * @return
     * @date ${createDate}
     */
    List<${entityClassName}> likePage(${entityClassName} ${entityName}, RowBounds rowBounds);
	
	/**
     * 模糊匹配，不进行分页
     * @param ${entityName}
     * @return
     * @date ${createDate}
     */
    List<${entityClassName}> like(${entityClassName} ${entityName});
	
	/**
     * 获取记录总条数
     * @param ${entityName}
     * @return
     * @date ${createDate}
     */
    int rows(${entityClassName} ${entityName});
}