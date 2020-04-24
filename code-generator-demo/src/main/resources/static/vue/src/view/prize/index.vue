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
            <el-button type="primary" icon="el-icon-plus" @click="handleCreate">新增Prize</el-button>
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
      <el-table-column label="奖品表id" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="管理员id" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.adminId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="奖品等级" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.grade }}</span>
        </template>
      </el-table-column>
      <el-table-column label="奖品名称" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.prize }}</span>
        </template>
      </el-table-column>
      <el-table-column label="奖品排序" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.serial }}</span>
        </template>
      </el-table-column>
      <el-table-column label="奖品数量" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.stock }}</span>
        </template>
      </el-table-column>
      <el-table-column label="奖品重置数量" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.resetStock }}</span>
        </template>
      </el-table-column>
      <el-table-column label="奖品图片" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.image }}</span>
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
      :title="prize.id ? '修改Prize' : '新增Prize'"
      :visible.sync="dialogFormVisible"
      @close="handleFormClose('prizeForm')"
    >
      <el-form ref="prizeForm" :model="prize" :rules="prizeRules" label-width="130px" label-suffix=":">
      <el-form-item label="奖品表id" prop="id">
        <el-input v-model="prize.id" placeholder="请输入奖品表id" />
      </el-form-item>
      <el-form-item label="管理员id" prop="adminId">
        <el-input v-model="prize.adminId" placeholder="请输入管理员id" />
      </el-form-item>
      <el-form-item label="奖品等级" prop="grade">
        <el-input v-model="prize.grade" placeholder="请输入奖品等级" />
      </el-form-item>
      <el-form-item label="奖品名称" prop="prize">
        <el-input v-model="prize.prize" placeholder="请输入奖品名称" />
      </el-form-item>
      <el-form-item label="奖品排序" prop="serial">
        <el-input v-model="prize.serial" placeholder="请输入奖品排序" />
      </el-form-item>
      <el-form-item label="奖品数量" prop="stock">
        <el-input v-model="prize.stock" placeholder="请输入奖品数量" />
      </el-form-item>
      <el-form-item label="奖品重置数量" prop="resetStock">
        <el-input v-model="prize.resetStock" placeholder="请输入奖品重置数量" />
      </el-form-item>
      <el-form-item label="奖品图片" prop="image">
        <el-input v-model="prize.image" placeholder="请输入奖品图片" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-input v-model="prize.createTime" placeholder="请输入创建时间" />
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-input v-model="prize.updateTime" placeholder="请输入更新时间" />
      </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleFormClose('prizeForm')">返回</el-button>
        <el-button type="primary" @click="handleSubmit('prizeForm')">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchPrizeList, fetchPrize, createPrize, updatePrize, deletePrize } from '@/api/prize/index';
import Pagination from '@/components/Pagination';

export default {
  name: 'Prize',
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
      prize: {
        id: '',
        adminId: '',
        grade: '',
        prize: '',
        serial: '',
        stock: '',
        resetStock: '',
        image: '',
        createTime: '',
        updateTime: ''
      },
      prizeRules: {
        id: [{ required: true, message: '奖品表id不能为空', trigger: 'blur' }],
        adminId: [{ required: true, message: '管理员id不能为空', trigger: 'blur' }],
        grade: [{ required: true, message: '奖品等级不能为空', trigger: 'blur' }],
        prize: [{ required: true, message: '奖品名称不能为空', trigger: 'blur' }],
        serial: [{ required: true, message: '奖品排序不能为空', trigger: 'blur' }],
        stock: [{ required: true, message: '奖品数量不能为空', trigger: 'blur' }],
        resetStock: [{ required: true, message: '奖品重置数量不能为空', trigger: 'blur' }],
        image: [{ required: true, message: '奖品图片不能为空', trigger: 'blur' }],
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
      fetchPrizeList(this.listQuery)
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
      this.prize = {};
    },
    handleEdit(index, row) {
      fetchPrize(row.id).then(res => {
        this.dialogFormVisible = true;
        this.prize = res;
      });
    },
    cancelDelete: function(id) {
      this.$refs[id].doClose();
    },
    handleDelete: function(index, row) {
      deletePrize(row.id).then(() => {
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
      this.prize = {};
      this.dialogFormVisible = false;
    },
    handleSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (!this.prize.id) {
            createPrize(this.prize).then(res => {
              this.dialogFormVisible = false;
              this.getList();
            });
          } else {
            updatePrize(this.prize).then(res => {
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
