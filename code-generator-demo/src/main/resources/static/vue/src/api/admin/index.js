import request from '@/utils/request'

export function fetchAdminList(query) {
  return request({
    url: '/mm/admin',
    method: 'GET',
    params: query
  })
}

export function fetchAdmin(id) {
  return request({
    url: '/mm/admin/' + id,
    method: 'GET'
})
}

export function createAdmin(formData) {
  return request({
    url: '/mm/admin',
    method: 'POST',
    data: formData
  })
}

export function updateAdmin(formData) {
  return request({
    url: '/mm/admin/' + formData.id,
    method: 'PUT',
    data: formData
  })
}

export function deleteAdmin(ids) {
  return request({
    url: '/mm/admin/' + ids,
    method: 'DELETE'
  })
}
