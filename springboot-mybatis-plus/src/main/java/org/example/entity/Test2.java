package org.example.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("test2")
public class Test2 extends Model<Test2> {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String username;

    private Integer parentId;

    private BigDecimal amount;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
