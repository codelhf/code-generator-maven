import request from '@/utils/request'

export function fetchSysLogList(query) {
  return request({
    url: '/mm/sysLog',
    method: 'GET',
    params: query
  })
}

export function fetchSysLog(id) {
  return request({
    url: '/mm/sysLog/' + id,
    method: 'GET'
})
}

export function createSysLog(formData) {
  return request({
    url: '/mm/sysLog',
    method: 'POST',
    data: formData
  })
}

export function updateSysLog(formData) {
  return request({
    url: '/mm/sysLog/' + formData.id,
    method: 'PUT',
    data: formData
  })
}

export function deleteSysLog(ids) {
  return request({
    url: '/mm/sysLog/' + ids,
    method: 'DELETE'
  })
}
