<template>
  <div class="page-container">
    <!-- 搜索表单 -->
    <el-form :model="listQuery" :inline="true" label-width="120px" label-suffix=":">
      <el-row>
        <el-form-item label="关键字">
          <el-input v-model="listQuery.condition" placeholder="请输入关键字" />
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="24" style="text-align: center">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
            <el-button type="primary" icon="el-icon-refresh-left" @click="handleReset">重置</el-button>
            <el-button type="primary" icon="el-icon-plus" @click="handleCreate">新增SysLog</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <!-- 列表 -->
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      highlight-current-row
      border="border"
    >
      <el-table-column label="记录id" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6角色用户关系，7角色权限关系" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.type }}</span>
        </template>
      </el-table-column>
      <el-table-column label="基于type制定后的对象id，比如用户，权限，角色等表的主键" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.targetId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="旧值" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.oldValue }}</span>
        </template>
      </el-table-column>
      <el-table-column label="新值" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.newValue }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作者" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.operator }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后一次操作时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.operateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后一次更新者的IP" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.operateIp }}</span>
        </template>
      </el-table-column>
      <el-table-column label="当前是否复原过，0：没有，1：复原过" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.status }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="160">
        <template slot-scope="scope">
          <el-button size="mini" circle title="编辑" icon="el-icon-edit-outline" @click="handleEdit(scope.$index, scope.row)"/>
          <el-popover :ref="scope.$index" placement="top" width="160" trigger="click">
            <p>确定删除吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="cancelDelete(scope.$index)">取消</el-button>
              <el-button type="primary" size="mini" @click="handleDelete(scope.$index, scope.row)">确定</el-button>
            </div>
            <el-button size="mini" circle title="删除" icon="el-icon-delete action_icon" slot="reference" />
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.currentPage"
      :limit.sync="listQuery.pageLimit"
      @pagination="getList"
    />
    <!-- dialog表单 -->
    <el-dialog
      :title="sysLog.id ? '修改SysLog' : '新增SysLog'"
      :visible.sync="dialogFormVisible"
      @close="handleFormClose('sysLogForm')"
    >
      <el-form ref="sysLogForm" :model="sysLog" :rules="sysLogRules" label-width="130px" label-suffix=":">
        <el-form-item label="记录id" prop="id">
          <el-input v-model="sysLog.id" placeholder="请输入记录id" />
        </el-form-item>
        <el-form-item label="权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6角色用户关系，7角色权限关系" prop="type">
          <el-input v-model="sysLog.type" placeholder="请输入权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6角色用户关系，7角色权限关系" />
        </el-form-item>
        <el-form-item label="基于type制定后的对象id，比如用户，权限，角色等表的主键" prop="targetId">
          <el-input v-model="sysLog.targetId" placeholder="请输入基于type制定后的对象id，比如用户，权限，角色等表的主键" />
        </el-form-item>
        <el-form-item label="旧值" prop="oldValue">
          <el-input v-model="sysLog.oldValue" placeholder="请输入旧值" />
        </el-form-item>
        <el-form-item label="新值" prop="newValue">
          <el-input v-model="sysLog.newValue" placeholder="请输入新值" />
        </el-form-item>
        <el-form-item label="操作者" prop="operator">
          <el-input v-model="sysLog.operator" placeholder="请输入操作者" />
        </el-form-item>
        <el-form-item label="最后一次操作时间" prop="operateTime">
          <el-input v-model="sysLog.operateTime" placeholder="请输入最后一次操作时间" />
        </el-form-item>
        <el-form-item label="最后一次更新者的IP" prop="operateIp">
          <el-input v-model="sysLog.operateIp" placeholder="请输入最后一次更新者的IP" />
        </el-form-item>
        <el-form-item label="当前是否复原过，0：没有，1：复原过" prop="status">
          <el-input v-model="sysLog.status" placeholder="请输入当前是否复原过，0：没有，1：复原过" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleFormClose('sysLogForm')">返回</el-button>
        <el-button type="primary" @click="handleFormSubmit('sysLogForm')">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchSysLogList, fetchSysLog, createSysLog, updateSysLog, deleteSysLog } from '@/api/sysLog/index';
import Pagination from '@/components/Pagination';

export default {
  name: 'SysLog',
  components: {
    Pagination
  },
  data() {
    return {
      tableKey: 0,
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        currentPage: 1,
        pageLimit: 10,
        sort: '-update_time',
        condition: '',
        opType: 'sysLog',
        opTime: new Date().getTime()
      },
      dialogFormVisible: false,
      sysLog: {
        id: '',
        type: '',
        targetId: '',
        oldValue: '',
        newValue: '',
        operator: '',
        operateTime: '',
        operateIp: '',
        status: ''
      },
      sysLogRules: {
        id: [{ required: true, message: '记录id不能为空', trigger: 'blur' }],
        type: [{ required: true, message: '权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6角色用户关系，7角色权限关系不能为空', trigger: 'blur' }],
        targetId: [{ required: true, message: '基于type制定后的对象id，比如用户，权限，角色等表的主键不能为空', trigger: 'blur' }],
        oldValue: [{ required: true, message: '旧值不能为空', trigger: 'blur' }],
        newValue: [{ required: true, message: '新值不能为空', trigger: 'blur' }],
        operator: [{ required: true, message: '操作者不能为空', trigger: 'blur' }],
        operateTime: [{ required: true, message: '最后一次操作时间不能为空', trigger: 'blur' }],
        operateIp: [{ required: true, message: '最后一次更新者的IP不能为空', trigger: 'blur' }],
        status: [{ required: true, message: '当前是否复原过，0：没有，1：复原过不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.listLoading = true;
      fetchSysLogList(this.listQuery)
        .then(response => {
          this.list = response.data;
          this.total = response.totalCount;
          // Just to simulate the time of the request
          setTimeout(() => {
            this.listLoading = false;
          }, 1.5 * 1000);
        });
    },
    handleFilter() {
      this.listQuery.currentPage = 1;
      this.getList();
    },
    handleReset() {
      this.listQuery = {
        currentPage: 1,
        pageLimit: 10,
        sort: '-update_time',
        condition: '',
        opType: 'sysLog',
        opTime: new Date().getTime()
      }
    },
    handleCreate() {
      this.dialogFormVisible = true;
      this.sysLog = {};
    },
    handleEdit(index, row) {
      fetchSysLog(row.id).then(res => {
        this.dialogFormVisible = true;
        this.sysLog = res;
      });
    },
    cancelDelete: function(id) {
      this.$refs[id].doClose();
    },
    handleDelete: function(index, row) {
      deleteSysLog(row.id).then(() => {
        this.$refs[index].doClose();
        this.$message({
          type: 'success',
          message: '删除成功',
          duration: 1500,
          forbidClick: true
        });
        this.getList();
      });
    },
    handleFormClose: function(formName) {
      this.$refs[formName].resetFields();
      this.sysLog = {};
      this.dialogFormVisible = false;
    },
    handleFormSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (!this.sysLog.id) {
            createSysLog(this.sysLog).then(res => {
              this.dialogFormVisible = false;
              this.getList();
            });
          } else {
            updateSysLog(this.sysLog).then(res => {
              this.dialogFormVisible = false;
              this.getList();
            });
          }
        }
      });
    }
  }
}
</script>

<style scoped>

</style>
