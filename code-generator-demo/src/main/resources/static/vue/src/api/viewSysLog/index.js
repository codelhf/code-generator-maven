import request from '@/utils/request'

export function fetchViewSysLogList(query) {
  return request({
    url: '/mm/viewSysLog',
    method: 'GET',
    params: query
  })
}

export function fetchViewSysLog(id) {
  return request({
    url: '/mm/viewSysLog/' + id,
    method: 'GET'
})
}

export function createViewSysLog(formData) {
  return request({
    url: '/mm/viewSysLog',
    method: 'POST',
    data: formData
  })
}

export function updateViewSysLog(formData) {
  return request({
    url: '/mm/viewSysLog/' + formData.id,
    method: 'PUT',
    data: formData
  })
}

export function deleteViewSysLog(ids) {
  return request({
    url: '/mm/viewSysLog/' + ids,
    method: 'DELETE'
  })
}
