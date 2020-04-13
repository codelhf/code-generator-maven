<template>
  <div class="page-container">
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
            <el-button type="primary" icon="el-icon-plus" @click="handleCreate">新增Result</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      highlight-current-row
      border="border"
    >
      <el-table-column label="用户名或昵称" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="奖品名称" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.prize }}</span>
        </template>
      </el-table-column>
      <el-table-column label="奖品等级" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.grade }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="160">
        <template slot-scope="scope">
          <i title="编辑" class="el-icon-edit-outline action_icon" @click="handleEdit(scope.$index, scope.row)"/>
          <template>
            <el-popover :ref="scope.$index" placement="top" width="160" trigger="click">
              <i slot="reference" title="删除" class="el-icon-delete action_icon" />
              <p>确定删除吗？</p>
              <div style="text-align: right; margin: 0">
                <el-button size="mini" type="text" @click="cancelDelete(scope.$index)">取消</el-button>
                <el-button type="primary" size="mini" @click="handleDelete(scope.$index, scope.row)">确定</el-button>
              </div>
            </el-popover>
          </template>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.currentPage" :limit.sync="listQuery.pageLimit" @pagination="getList" />

    <el-dialog
      :title="result.id ? '修改Result' : '新增Result'"
      :visible.sync="dialogFormVisible"
      @close="handleFormClose('resultForm')"
    >
      <el-form ref="resultForm" :model="result" :rules="resultRules" label-width="130px" label-suffix=":">
      <el-form-item label="用户名或昵称" prop="username">
        <el-input v-model="result.username" placeholder="请输入用户名或昵称" />
      </el-form-item>
      <el-form-item label="奖品名称" prop="prize">
        <el-input v-model="result.prize" placeholder="请输入奖品名称" />
      </el-form-item>
      <el-form-item label="奖品等级" prop="grade">
        <el-input v-model="result.grade" placeholder="请输入奖品等级" />
      </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleFormClose('resultForm')">返回</el-button>
        <el-button type="primary" @click="handleSubmit('resultForm')">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchResultList, fetchResult, createResult, updateResult, deleteResult } from '@/api/result/index';
import Pagination from '@/components/Pagination';

export default {
  name: 'Result',
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
      result: {
        username: '',
        prize: '',
        grade: ''
      },
      resultRules: {
        username: [{ required: true, message: '用户名或昵称不能为空', trigger: 'blur' }],
        prize: [{ required: true, message: '奖品名称不能为空', trigger: 'blur' }],
        grade: [{ required: true, message: '奖品等级不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.listLoading = true;
      fetchResultList(this.listQuery)
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
      this.result = {};
    },
    handleEdit(index, row) {
      fetchResult(row.id).then(res => {
        this.dialogFormVisible = true;
        this.result = res;
      });
    },
    handleDelete: function(index, row) {
      deleteResult(row.id).then(() => {
        this.getList();
        this.$refs[index].doClose();
        this.$message({
          type: 'success',
          message: '删除成功',
          duration: 1500,
          forbidClick: true
        });
      });
    },
    cancelDelete: function(id) {
      this.$refs[id].doClose();
    },
    handleFormClose: function(formName) {
      this.$refs[formName].resetFields();
      this.result = {};
      this.dialogFormVisible = false;
    },
    handleSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (!this.result.id) {
            createResult(this.result).then(res => {
              this.dialogFormVisible = false;
              this.getList();
            })
          } else {
            updateResult(this.result).then(res => {
              this.dialogFormVisible = false;
              this.getList();
            })
          }
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
