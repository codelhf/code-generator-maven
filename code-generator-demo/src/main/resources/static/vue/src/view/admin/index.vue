<template>
  <div class="page-container">
    <!-- 搜索表单 -->
    <el-form :model="listQuery" :inline="true" label-width="120px" label-suffix=":">
      <el-row>
        <el-form-item label="关键字">
          <el-input v-model="listQuery.keyword" placeholder="请输入关键字" />
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="24" style="text-align: center">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
            <el-button type="primary" icon="el-icon-refresh" @click="handleReset">重置</el-button>
            <el-button type="primary" icon="el-icon-plus" @click="handleCreate">新增Admin</el-button>
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
      <el-table-column label="管理员表" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="管理员头像" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.headImg }}</span>
        </template>
      </el-table-column>
      <el-table-column label="管理员名称" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="管理员密码" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.password }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.updateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="160">
        <template slot-scope="scope">
          <el-button size="mini" cricle title="编辑" icon="el-icon-edit-outline" @click="handleEdit(scope.$index, scope.row)"/>
          <el-popover :ref="scope.$index" placement="top" width="160" trigger="click">
            <p>确定删除吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="cancelDelete(scope.$index)">取消</el-button>
              <el-button type="primary" size="mini" @click="handleDelete(scope.$index, scope.row)">确定</el-button>
            </div>
            <el-button size="mini" cricle title="删除" icon="el-icon-delete action_icon" slot="reference" />
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
       @pagination="getList" />
    <!-- dialog表单 -->
    <el-dialog
      :title="admin.id ? '修改Admin' : '新增Admin'"
      :visible.sync="dialogFormVisible"
      @close="handleFormClose('adminForm')"
    >
      <el-form ref="adminForm" :model="admin" :rules="adminRules" label-width="130px" label-suffix=":">
      <el-form-item label="管理员表" prop="id">
        <el-input v-model="admin.id" placeholder="请输入管理员表" />
      </el-form-item>
      <el-form-item label="管理员头像" prop="headImg">
        <el-input v-model="admin.headImg" placeholder="请输入管理员头像" />
      </el-form-item>
      <el-form-item label="管理员名称" prop="username">
        <el-input v-model="admin.username" placeholder="请输入管理员名称" />
      </el-form-item>
      <el-form-item label="管理员密码" prop="password">
        <el-input v-model="admin.password" placeholder="请输入管理员密码" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-input v-model="admin.createTime" placeholder="请输入创建时间" />
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-input v-model="admin.updateTime" placeholder="请输入更新时间" />
      </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleFormClose('adminForm')">返回</el-button>
        <el-button type="primary" @click="handleSubmit('adminForm')">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchAdminList, fetchAdmin, createAdmin, updateAdmin, deleteAdmin } from '@/api/admin/index';
import Pagination from '@/components/Pagination';

export default {
  name: 'Admin',
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
        sort: '+update_time',
        keyword: ''
      },
      dialogFormVisible: false,
      admin: {
        id: '',
        headImg: '',
        username: '',
        password: '',
        createTime: '',
        updateTime: ''
      },
      adminRules: {
        id: [{ required: true, message: '管理员表不能为空', trigger: 'blur' }],
        headImg: [{ required: true, message: '管理员头像不能为空', trigger: 'blur' }],
        username: [{ required: true, message: '管理员名称不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '管理员密码不能为空', trigger: 'blur' }],
        createTime: [{ required: true, message: '创建时间不能为空', trigger: 'blur' }],
        updateTime: [{ required: true, message: '更新时间不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.listLoading = true;
      fetchAdminList(this.listQuery)
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
        sort: '+update_time',
        keyword: ''
      }
    },
    handleCreate() {
      this.dialogFormVisible = true;
      this.admin = {};
    },
    handleEdit(index, row) {
      fetchAdmin(row.id).then(res => {
        this.dialogFormVisible = true;
        this.admin = res;
      });
    },
    cancelDelete: function(id) {
      this.$refs[id].doClose();
    },
    handleDelete: function(index, row) {
      deleteAdmin(row.id).then(() => {
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
      this.admin = {};
      this.dialogFormVisible = false;
    },
    handleSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (!this.admin.id) {
            createAdmin(this.admin).then(res => {
              this.dialogFormVisible = false;
              this.getList();
            });
          } else {
            updateAdmin(this.admin).then(res => {
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
