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
            <el-button type="primary" icon="el-icon-plus" @click="handleCreate">新增User</el-button>
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
      <el-table-column label="ID" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="管理员id" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.adminId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户所获奖品ID" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.prizeId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户头像" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.headImg }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户名或昵称" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户备注" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.description }}</span>
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
      :title="user.id ? '修改User' : '新增User'"
      :visible.sync="dialogFormVisible"
      @close="handleFormClose('userForm')"
    >
      <el-form ref="userForm" :model="user" :rules="userRules" label-width="130px" label-suffix=":">
      <el-form-item label="ID" prop="id">
        <el-input v-model="user.id" placeholder="请输入ID" />
      </el-form-item>
      <el-form-item label="管理员id" prop="adminId">
        <el-input v-model="user.adminId" placeholder="请输入管理员id" />
      </el-form-item>
      <el-form-item label="用户所获奖品ID" prop="prizeId">
        <el-input v-model="user.prizeId" placeholder="请输入用户所获奖品ID" />
      </el-form-item>
      <el-form-item label="用户头像" prop="headImg">
        <el-input v-model="user.headImg" placeholder="请输入用户头像" />
      </el-form-item>
      <el-form-item label="用户名或昵称" prop="username">
        <el-input v-model="user.username" placeholder="请输入用户名或昵称" />
      </el-form-item>
      <el-form-item label="用户备注" prop="description">
        <el-input v-model="user.description" placeholder="请输入用户备注" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-input v-model="user.createTime" placeholder="请输入创建时间" />
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-input v-model="user.updateTime" placeholder="请输入更新时间" />
      </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleFormClose('userForm')">返回</el-button>
        <el-button type="primary" @click="handleSubmit('userForm')">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchUserList, fetchUser, createUser, updateUser, deleteUser } from '@/api/user/index';
import Pagination from '@/components/Pagination';

export default {
  name: 'User',
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
      user: {
        id: '',
        adminId: '',
        prizeId: '',
        headImg: '',
        username: '',
        description: '',
        createTime: '',
        updateTime: ''
      },
      userRules: {
        id: [{ required: true, message: 'ID不能为空', trigger: 'blur' }],
        adminId: [{ required: true, message: '管理员id不能为空', trigger: 'blur' }],
        prizeId: [{ required: true, message: '用户所获奖品ID不能为空', trigger: 'blur' }],
        headImg: [{ required: true, message: '用户头像不能为空', trigger: 'blur' }],
        username: [{ required: true, message: '用户名或昵称不能为空', trigger: 'blur' }],
        description: [{ required: true, message: '用户备注不能为空', trigger: 'blur' }],
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
      fetchUserList(this.listQuery)
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
      this.user = {};
    },
    handleEdit(index, row) {
      fetchUser(row.id).then(res => {
        this.dialogFormVisible = true;
        this.user = res;
      });
    },
    cancelDelete: function(id) {
      this.$refs[id].doClose();
    },
    handleDelete: function(index, row) {
      deleteUser(row.id).then(() => {
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
      this.user = {};
      this.dialogFormVisible = false;
    },
    handleSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (!this.user.id) {
            createUser(this.user).then(res => {
              this.dialogFormVisible = false;
              this.getList();
            });
          } else {
            updateUser(this.user).then(res => {
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
